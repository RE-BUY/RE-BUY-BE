package com.rebuy.controller.dto.dashboard;

import com.rebuy.controller.dto.order.OrderResponse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class DashboardResponse {
    private String username;
    private BigDecimal creditBalance;
    private BigDecimal environmentScore;
    private long pineTreeEquivalent;
    private List<OrderResponse> recentOrders;
}