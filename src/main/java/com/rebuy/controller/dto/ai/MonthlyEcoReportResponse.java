package com.rebuy.controller.dto.ai;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyEcoReportResponse {
    private String username;
    private int year;
    private int month;
    private LocalDate reportGeneratedAt;

    // 구매 통계
    private PurchaseStats purchaseStats;

    // 환경 기여도
    private EnvironmentalImpact environmentalImpact;

    // AI 분석 및 인사이트
    private String aiAnalysis;
    private List<String> achievements;
    private List<String> recommendations;

    // 순위 및 비교
    private RankingInfo ranking;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PurchaseStats {
        private int totalOrders;
        private BigDecimal totalSpent;
        private BigDecimal averageOrderValue;
        private Map<String, Integer> categoryDistribution;
        private BigDecimal averageEcoScore;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnvironmentalImpact {
        private BigDecimal co2Saved;
        private BigDecimal waterSaved;
        private BigDecimal oilSaved;
        private BigDecimal plasticSaved;
        private String equivalentTo; // AI 생성 비유 (예: "나무 10그루 심기와 같은 효과")
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RankingInfo {
        private int userRank;
        private int totalUsers;
        private String rankPercentile; // 상위 10%
        private BigDecimal comparedToAverage; // 평균 대비 몇 %
    }
}

