package com.rebuy.service;

import com.rebuy.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class EcoImpactCalculator {

    @Value("${eco.weight.co2:0.8}")
    private BigDecimal weightCo2;

    @Value("${eco.weight.water:0.05}")
    private BigDecimal weightWater;

    @Value("${eco.weight.oil:0.10}")
    private BigDecimal weightOil;

    @Value("${eco.weight.plastic:0.15}")
    private BigDecimal weightPlastic;

    public BigDecimal calculateDynamicEcoScore(Product p) {
        BigDecimal base = safe(p.getEcoBaseScore());
        BigDecimal co2 = safe(p.getSavedCo2Kg()).multiply(weightCo2);
        BigDecimal water = safe(p.getSavedWaterL()).multiply(weightWater);
        BigDecimal oil = safe(p.getSavedOilMl()).multiply(weightOil);
        BigDecimal plastic = safe(p.getSavedPlasticG()).multiply(weightPlastic);
        return base.add(co2).add(water).add(oil).add(plastic)
                .setScale(4, RoundingMode.HALF_UP);
    }

    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}