package com.rebuy.controller.dto.order;

import com.rebuy.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class OrderSummaryResponse {
    private Long orderId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private BigDecimal amountPaid;
    private BigDecimal creditUsed;
    private BigDecimal creditEarned;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
}