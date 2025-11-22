package com.rebuy.dto.order;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderPageResponse {
    private List<OrderSummaryResponse> items;
    private long total;
    private int page;
    private int size;
}