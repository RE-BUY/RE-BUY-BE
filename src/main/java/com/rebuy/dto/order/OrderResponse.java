package com.rebuy.dto.order;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
public class OrderResponse {
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal totalProductAmount;
    private BigDecimal creditUsed;
    private BigDecimal creditEarned;
    private BigDecimal amountPaid;
    private Instant orderedAt;
    private BigDecimal environmentScoreGain;
    private BigDecimal savedCo2KgGain;
    private BigDecimal savedWaterLGain;
    private BigDecimal savedOilMlGain;
    private BigDecimal savedPlasticGGain;
}