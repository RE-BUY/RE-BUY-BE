package com.rebuy.dto.product;

import com.rebuy.entity.enums.ProductCategory;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private ProductCategory category;
    private String description;
    private String manufacturer;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;
    private BigDecimal ecoScore;
}