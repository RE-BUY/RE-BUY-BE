package com.rebuy.dto.mypage;

import com.rebuy.entity.enums.OrderStatus; // OrderStatus 경로 확인 필요!
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderSummaryDto(
        Long orderId,
        LocalDateTime orderDate,
        String representativeProductName, // "친환경 칫솔 외 2건"
        BigDecimal totalAmount,
        OrderStatus status
) {}