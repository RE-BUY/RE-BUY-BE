package com.rebuy.service;

import com.rebuy.controller.dto.mypage.EcoStatDto;
import com.rebuy.controller.dto.mypage.MyPageResponse;
import com.rebuy.controller.dto.mypage.OrderSummaryDto;
import com.rebuy.entity.Order;
import com.rebuy.entity.User;
import com.rebuy.repository.OrderRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    // 상수: 소나무 1그루 = 환경점수 100점 (가정)
    private static final BigDecimal SCORE_PER_PINE_TREE = BigDecimal.valueOf(100);

    public MyPageResponse getMyPageData(Long userId) {
        // 1. 사용자 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 2. 소나무 그루 수 계산 (BigDecimal 나눗셈)
        // 환경점수 / 100 -> 정수로 변환
        int pineTreeCount = user.getEnvironmentScore()
                .divide(SCORE_PER_PINE_TREE, 0, RoundingMode.DOWN)
                .intValue();

        // 3. 최근 주문 내역 5건 조회
        List<Order> recentOrders = orderRepository.findByUserIdOrderByCreatedAtDesc(
                userId, PageRequest.of(0, 5)
        ).getContent();

        // 주문 엔티티 -> DTO 변환 로직
        List<OrderSummaryDto> orderDtos = recentOrders.stream()
                .map(this::convertToOrderSummary)
                .collect(Collectors.toList());

        // 4. 월별 통계 데이터 조회 (그래프용)
        List<EcoStatDto> monthlyStats = orderRepository.findMonthlyEcoStats(userId);

        return new MyPageResponse(
                user.getUsername(),
                user.getCreditBalance(),
                user.getEnvironmentScore(),
                pineTreeCount,
                orderDtos,
                monthlyStats
        );
    }

    // Order 엔티티를 요약 DTO로 변환하는 헬퍼 메서드
    private OrderSummaryDto convertToOrderSummary(Order order) {
        String productName = "";
        int size = order.getItems().size();

        if (size > 0) {
            String firstItemName = order.getItems().get(0).getProduct().getName();
            if (size > 1) {
                productName = firstItemName + " 외 " + (size - 1) + "건";
            } else {
                productName = firstItemName;
            }
        }

        return new OrderSummaryDto(
                order.getId(),
                order.getCreatedAt(),
                productName,
                order.getTotalAmount(),
                order.getStatus()
        );
    }
}