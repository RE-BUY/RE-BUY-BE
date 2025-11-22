package com.rebuy.service;

import com.rebuy.controller.dto.order.CheckoutRequest;
import com.rebuy.controller.dto.order.OrderDetailResponse;
import com.rebuy.controller.dto.order.OrderDetailItemResponse;
import com.rebuy.entity.*;
import com.rebuy.entity.enums.CreditTransactionType;
import com.rebuy.entity.enums.OrderStatus;
import com.rebuy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CreditTransactionRepository creditTransactionRepository;
    private final EcoImpactCalculator ecoImpactCalculator; // 동적 점수 계산기 (있다면)

    @Transactional
    public OrderDetailResponse checkout(CheckoutRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        // 주문 엔티티 초기화
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalCreditUsed(BigDecimal.ZERO);
        order.setTotalCreditEarned(BigDecimal.ZERO);
        order.setEnvironmentScoreGain(BigDecimal.ZERO); // 있으면

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal environmentGain = BigDecimal.ZERO;

        // 사용자 누적 통계용 변수
        BigDecimal totalCo2 = BigDecimal.ZERO;
        BigDecimal totalWater = BigDecimal.ZERO;
        BigDecimal totalOil = BigDecimal.ZERO;
        BigDecimal totalPlastic = BigDecimal.ZERO;

        // 아이템 매핑
        for (CartItem ci : cartItems) {
            Product p = ci.getProduct();
            int qty = ci.getQuantity();

            BigDecimal unitPrice = p.getPrice();
            BigDecimal lineAmount = unitPrice.multiply(BigDecimal.valueOf(qty));
            totalAmount = totalAmount.add(lineAmount);

            // 환경 점수 (동적): ecoImpactCalculator.calculateDynamicEcoScore(p)
            BigDecimal itemEcoScore = ecoImpactCalculator != null
                    ? ecoImpactCalculator.calculateDynamicEcoScore(p)
                    : safe(p.getEcoScore());

            environmentGain = environmentGain.add(itemEcoScore.multiply(BigDecimal.valueOf(qty)));

            // LCI 값 누적 (수량 반영)
            BigDecimal qtyDecimal = BigDecimal.valueOf(qty);
            totalCo2 = totalCo2.add(safe(p.getSavedCo2Kg()).multiply(qtyDecimal));
            totalWater = totalWater.add(safe(p.getSavedWaterL()).multiply(qtyDecimal));
            totalOil = totalOil.add(safe(p.getSavedOilMl()).multiply(qtyDecimal));
            totalPlastic = totalPlastic.add(safe(p.getSavedPlasticG()).multiply(qtyDecimal));

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(p);
            oi.setQuantity(qty);
            oi.setUnitPrice(unitPrice);
            oi.setLineAmount(lineAmount);
            order.getItems().add(oi);

            // 재고 차감
            p.setStock(p.getStock() - qty);
        }

        // 크레딧 사용/적립(단순 예시)
        BigDecimal creditUsed = user.getCreditBalance().min(totalAmount); // 전액 사용 또는 부분
        BigDecimal amountPaid = totalAmount.subtract(creditUsed);
        BigDecimal creditEarned = amountPaid.multiply(BigDecimal.valueOf(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP);

        order.setTotalAmount(totalAmount);
        order.setAmountPaid(amountPaid);
        order.setTotalCreditUsed(creditUsed);
        order.setTotalCreditEarned(creditEarned);
        order.setEnvironmentScoreGain(environmentGain);

        // 사용자 업데이트
        user.setCreditBalance(user.getCreditBalance().subtract(creditUsed).add(creditEarned));
        user.setEnvironmentScore(user.getEnvironmentScore().add(environmentGain));

        // 누적 환경 통계 업데이트
        user.setTotalSavedCo2Kg(user.getTotalSavedCo2Kg().add(totalCo2));
        user.setTotalSavedWaterL(user.getTotalSavedWaterL().add(totalWater));
        user.setTotalSavedOilMl(user.getTotalSavedOilMl().add(totalOil));
        user.setTotalSavedPlasticG(user.getTotalSavedPlasticG().add(totalPlastic));

        // 크레딧 사용 트랜잭션 기록
        if (creditUsed.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal balanceAfterUse = user.getCreditBalance();
            creditTransactionRepository.save(CreditTransaction.builder()
                    .user(user)
                    .amount(creditUsed.negate())
                    .balanceAfter(balanceAfterUse)
                    .type(CreditTransactionType.USE)
                    .description("주문에서 크레딧 사용")
                    .build());
        }

        // 적립 트랜잭션
        if (creditEarned.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal balanceAfterEarn = user.getCreditBalance();
            creditTransactionRepository.save(CreditTransaction.builder()
                    .user(user)
                    .amount(creditEarned)
                    .balanceAfter(balanceAfterEarn)
                    .type(CreditTransactionType.EARN)
                    .description("주문 완료 적립")
                    .build());
        }

        orderRepository.save(order);
        cartItemRepository.deleteByUser(user);

        // 트랜잭션 레코드에 orderId 채우고 싶다면 저장 후 refId 업데이트 (선택)

        // Response 구성
        var itemResponses = order.getItems().stream()
                .map(i -> OrderDetailItemResponse.builder()
                        .productId(i.getProduct().getId())
                        .productName(i.getProduct().getName())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .lineAmount(i.getLineAmount())
                        .ecoScore(i.getProduct().getEcoScore())
                        .build())
                .collect(Collectors.toList());

        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .amountPaid(order.getAmountPaid())
                .creditUsed(order.getTotalCreditUsed())
                .creditEarned(order.getTotalCreditEarned())
                .environmentScoreGain(order.getEnvironmentScoreGain())
                .items(itemResponses)
                .build();
    }

    // 안전 BigDecimal
    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}