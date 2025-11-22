package com.rebuy.controller.dto.order;

import com.rebuy.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderDetailResponse {
    private Long orderId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private BigDecimal amountPaid;
    private BigDecimal creditUsed;
    private BigDecimal creditEarned;
    private BigDecimal environmentScoreGain;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
    private String receiverName;
    private String address;
    private String contactPhone;
    private List<OrderDetailItemResponse> items;
}