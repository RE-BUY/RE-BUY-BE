package com.rebuy.controller.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRecommendationResponse {
    private List<RecommendedProduct> recommendations;
    private String reason;
    private String aiInsight;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecommendedProduct {
        private Long productId;
        private String productName;
        private BigDecimal price;
        private String category;
        private BigDecimal ecoScore;
        private String reasonForRecommendation;
        private Double matchScore; // 0.0 ~ 1.0
    }
}

