package com.rebuy.controller.dto.mypage;

import java.math.BigDecimal;

public record EcoStatDto(
        String yearMonth,      // "2024-01"
        BigDecimal totalAmount, // 해당 월 총 주문 금액
        BigDecimal score        // 해당 월 환경 점수 합계
) {}