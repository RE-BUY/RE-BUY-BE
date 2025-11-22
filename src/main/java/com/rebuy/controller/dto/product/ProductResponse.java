package com.rebuy.controller.dto.product;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String category;
    private String description;
    private String manufacturer;
    private String imageUrl;
    private BigDecimal price;
    private Integer stock;

    private BigDecimal ecoBaseScore;
    private BigDecimal savedCo2Kg;
    private BigDecimal savedWaterL;
    private BigDecimal savedOilMl;
    private BigDecimal savedPlasticG;

    private BigDecimal dynamicEcoScore;
}