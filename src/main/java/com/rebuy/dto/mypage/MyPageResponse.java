package com.rebuy.dto.mypage;

import java.math.BigDecimal;
import java.util.List;

public record MyPageResponse(
        String username,
        BigDecimal totalCredit,      // 현재 보유 크레딧
        BigDecimal totalEcoScore,    // 누적 환경 점수
        int pineTreeCount,           // 소나무 환산 그루 수
        List<OrderSummaryDto> recentOrders, // 최근 주문 내역 (최대 5개)
        List<EcoStatDto> monthlyEcoStats    // 월별 환경 기여도 통계 (그래프용)
) {}