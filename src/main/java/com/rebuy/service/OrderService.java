package com.rebuy.service;

import com.rebuy.dto.order.OrderRequest;
import com.rebuy.dto.order.OrderResponse;
import com.rebuy.entity.*;
import com.rebuy.entity.enums.OrderStatus;
import com.rebuy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CreditTransactionRepository creditTransactionRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));

        int quantity = request.getQuantity() == null ? 1 : request.getQuantity();
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("재고 부족");
        }

        BigDecimal unitPrice = product.getPrice();
        BigDecimal totalProductAmount = unitPrice.multiply(BigDecimal.valueOf(quantity));

        // 크레딧 사용 로직
        BigDecimal userCredit = user.getCreditBalance();
        BigDecimal requestedCredit = (request.getCreditToUse() == null)
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(request.getCreditToUse());

        BigDecimal maxCreditUse = totalProductAmount.multiply(BigDecimal.valueOf(0.20));
        BigDecimal creditUsed = requestedCredit.min(userCredit).min(maxCreditUse);

        BigDecimal amountPaid = totalProductAmount.subtract(creditUsed);
        BigDecimal creditEarned = amountPaid.multiply(BigDecimal.valueOf(0.05));

        BigDecimal environmentGain = product.getEcoScore().multiply(BigDecimal.valueOf(quantity));

        user.setCreditBalance(userCredit.subtract(creditUsed).add(creditEarned));
        user.setEnvironmentScore(user.getEnvironmentScore().add(environmentGain));

        product.setStock(product.getStock() - quantity);

        Order order = Order.builder()
                .user(user)
                .status(OrderStatus.PENDING)
                .totalAmount(totalProductAmount)
                .totalCreditUsed(creditUsed)
                .build();
        orderRepository.save(order);

        OrderItem item = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .build();
        orderItemRepository.save(item);

        if (creditUsed.compareTo(BigDecimal.ZERO) > 0) {
            creditTransactionRepository.save(CreditTransaction.builder()
                    .user(user)
                    .amount(creditUsed.negate())
                    .balanceAfter(user.getCreditBalance().subtract(creditEarned)) // 적립 전 잔액
                    .type("USE")
                    .description("주문에 사용된 크레딧")
                    .build());
        }

        creditTransactionRepository.save(CreditTransaction.builder()
                .user(user)
                .amount(creditEarned)
                .balanceAfter(user.getCreditBalance())
                .type("EARN")
                .description("주문 크레딧 적립")
                .build());

        return OrderResponse.builder()
                .orderId(order.getId())
                .productId(product.getId())
                .productName(product.getName())
                .quantity(quantity)
                .totalProductAmount(totalProductAmount)
                .creditUsed(creditUsed)
                .creditEarned(creditEarned)
                .amountPaid(amountPaid)
                .orderedAt(Instant.now())
                .build();
    }
}