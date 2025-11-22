package com.rebuy.dto.product;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class AdminProductUpdateRequest {
    @Min(0)
    private Integer stock;

    private BigDecimal price;
    private BigDecimal ecoBaseScore;

    private BigDecimal savedCo2Kg;
    private BigDecimal savedWaterL;
    private BigDecimal savedOilMl;
    private BigDecimal savedPlasticG;
}