package com.rebuy.service;

import com.rebuy.dto.order.CheckoutRequest;
import com.rebuy.dto.order.OrderDetailResponse;
import com.rebuy.dto.order.OrderDetailItemResponse;
import com.rebuy.entity.*;
import com.rebuy.entity.enums.OrderStatus;
import com.rebuy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CreditTransactionRepository creditTransactionRepository;

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public OrderDetailResponse checkout(CheckoutRequest request) {
        User user = currentUser();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        // 총액 / 환경 점수 계산
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal environmentGain = BigDecimal.ZERO;

        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();
            // 재고 검증
            if (p.getStock() < ci.getQuantity()) {
                throw new IllegalArgumentException("재고 부족: " + p.getName());
            }
            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()));
            totalAmount = totalAmount.add(line);
            environmentGain = environmentGain.add(p.getEcoScore()
                    .multiply(BigDecimal.valueOf(ci.getQuantity())));
        }

        // 크레딧 사용
        BigDecimal requested = request.getCreditToUse() == null
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(request.getCreditToUse());
        BigDecimal maxUse = totalAmount.multiply(BigDecimal.valueOf(0.20));
        BigDecimal creditUsed = requested.min(user.getCreditBalance()).min(maxUse);

        BigDecimal amountPaid = totalAmount.subtract(creditUsed);
        BigDecimal creditEarned = amountPaid.multiply(BigDecimal.valueOf(0.05));

        // 사용자 업데이트
        user.setCreditBalance(user.getCreditBalance()
                .subtract(creditUsed)
                .add(creditEarned));
        user.setEnvironmentScore(user.getEnvironmentScore().add(environmentGain));

        // 재고 차감
        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();
            p.setStock(p.getStock() - ci.getQuantity());
        }

        // 주문 생성 (즉시 결제 처리: PAID)
        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PAID)
                .totalAmount(totalAmount)
                .totalCreditUsed(creditUsed)
                .totalCreditEarned(creditEarned)
                .amountPaid(amountPaid)
                .paidAt(LocalDateTime.now())
                .receiverName(request.getReceiverName())
                .address(request.getAddress())
                .contactPhone(request.getContactPhone())
                .build();

        // OrderItem 생성
        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();
            BigDecimal unit = p.getPrice();
            BigDecimal line = unit.multiply(BigDecimal.valueOf(ci.getQuantity()));
            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(p)
                    .quantity(ci.getQuantity())
                    .unitPrice(unit)
                    .lineAmount(line)
                    .build();
            order.getItems().add(item);
        }

        orderRepository.save(order);

        // 크레딧 트랜잭션 기록
        if (creditUsed.compareTo(BigDecimal.ZERO) > 0) {
            creditTransactionRepository.save(CreditTransaction.builder()
                    .user(user)
                    .amount(creditUsed.negate())
                    .balanceAfter(user.getCreditBalance().subtract(creditEarned))
                    .type("USE")
                    .description("체크아웃 크레딧 사용")
                    .build());
        }
        creditTransactionRepository.save(CreditTransaction.builder()
                .user(user)
                .amount(creditEarned)
                .balanceAfter(user.getCreditBalance())
                .type("EARN")
                .description("체크아웃 크레딧 적립")
                .build());

        // 장바구니 비우기
        cartItemRepository.deleteByUser(user);

        var itemResponses = order.getItems().stream()
                .map(i -> OrderDetailItemResponse.builder()
                        .productId(i.getProduct().getId())
                        .productName(i.getProduct().getName())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .lineAmount(i.getLineAmount())
                        .ecoScore(i.getProduct().getEcoScore())
                        .build()).toList();

        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(totalAmount)
                .amountPaid(amountPaid)
                .creditUsed(creditUsed)
                .creditEarned(creditEarned)
                .environmentScoreGain(environmentGain)
                .paidAt(order.getPaidAt())
                .createdAt(order.getCreatedAt())
                .receiverName(order.getReceiverName())
                .address(order.getAddress())
                .contactPhone(order.getContactPhone())
                .items(itemResponses)
                .build();
    }
}