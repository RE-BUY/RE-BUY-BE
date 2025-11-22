package com.rebuy.controller.dto.product;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductListResponse {
    private List<ProductResponse> items;
    private long total;
    private int page;
    private int size;
}