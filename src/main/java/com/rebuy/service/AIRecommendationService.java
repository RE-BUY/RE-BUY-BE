package com.rebuy.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.controller.dto.ai.MonthlyEcoReportResponse;
import com.rebuy.controller.dto.ai.ProductRecommendationResponse;
import com.rebuy.entity.Order;
import com.rebuy.entity.OrderItem;
import com.rebuy.entity.Product;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.ProductCategory;
import com.rebuy.global.exception.ResourceNotFoundException;
import com.rebuy.repository.OrderRepository;
import com.rebuy.repository.ProductRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AIRecommendationService {

    private final UpstageAIService upstageAIService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    /**
     * 사용자 맞춤형 상품 추천
     */
    public ProductRecommendationResponse recommendProducts(String username, int limit) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));

        // 1. 사용자 구매 패턴 분석
        List<Order> userOrders = orderRepository.findByUserOrderByCreatedAtDesc(user);
        Map<String, Object> purchasePattern = analyzePurchasePattern(user, userOrders);

        // 2. 모든 상품 조회
        List<Product> allProducts = productRepository.findAll();

        // 3. AI에게 추천 요청
        String systemPrompt = buildRecommendationSystemPrompt();
        String userPrompt = buildRecommendationUserPrompt(user, purchasePattern, allProducts, limit);

        String aiResponse = upstageAIService.generateCompletion(systemPrompt, userPrompt);

        // 4. AI 응답 파싱 및 추천 상품 생성
        return parseRecommendationResponse(aiResponse, allProducts);
    }

    /**
     * 월별 녹색 소비 리포트 생성
     */
    public MonthlyEcoReportResponse generateMonthlyReport(String username, int year, int month) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다."));

        // 1. 해당 월의 주문 데이터 수집
        YearMonth targetMonth = YearMonth.of(year, month);
        List<Order> monthlyOrders = getMonthlyOrders(user, targetMonth);

        if (monthlyOrders.isEmpty()) {
            return buildEmptyReport(user, year, month);
        }

        // 2. 통계 계산
        MonthlyEcoReportResponse.PurchaseStats purchaseStats = calculatePurchaseStats(monthlyOrders);
        MonthlyEcoReportResponse.EnvironmentalImpact impact = calculateEnvironmentalImpact(monthlyOrders);
        MonthlyEcoReportResponse.RankingInfo ranking = calculateRanking(user, targetMonth);

        // 3. AI 분석 및 인사이트 생성
        String systemPrompt = buildReportSystemPrompt();
        String userPrompt = buildReportUserPrompt(user, purchaseStats, impact, ranking, year, month);

        String aiAnalysis = upstageAIService.generateCompletion(systemPrompt, userPrompt);

        // 4. 성과 및 추천사항 추출
        Map<String, List<String>> aiInsights = parseReportInsights(aiAnalysis);

        return MonthlyEcoReportResponse.builder()
                .username(username)
                .year(year)
                .month(month)
                .reportGeneratedAt(LocalDate.now())
                .purchaseStats(purchaseStats)
                .environmentalImpact(impact)
                .aiAnalysis(aiAnalysis)
                .achievements(aiInsights.get("achievements"))
                .recommendations(aiInsights.get("recommendations"))
                .ranking(ranking)
                .build();
    }

    // ========== Private Helper Methods ==========

    private Map<String, Object> analyzePurchasePattern(User user, List<Order> orders) {
        Map<String, Object> pattern = new HashMap<>();

        // 구매 카테고리 분포
        Map<String, Long> categoryCount = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getCategory().name(),
                        Collectors.counting()
                ));

        // 평균 구매 금액
        BigDecimal avgOrderAmount = orders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(Math.max(orders.size(), 1)), 2, RoundingMode.HALF_UP);

        // 선호 가격대
        String priceRange = determinePriceRange(avgOrderAmount);

        // 환경 점수 선호도
        double avgEcoScore = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getProduct().getEcoScore().doubleValue())
                .average()
                .orElse(0.0);

        pattern.put("totalOrders", orders.size());
        pattern.put("categoryPreference", categoryCount);
        pattern.put("avgOrderAmount", avgOrderAmount);
        pattern.put("priceRange", priceRange);
        pattern.put("avgEcoScore", avgEcoScore);
        pattern.put("creditBalance", user.getCreditBalance());

        return pattern;
    }

    private String determinePriceRange(BigDecimal avgAmount) {
        if (avgAmount.compareTo(new BigDecimal("10000")) < 0) return "저가";
        if (avgAmount.compareTo(new BigDecimal("50000")) < 0) return "중가";
        return "고가";
    }

    private String buildRecommendationSystemPrompt() {
        return """
                당신은 친환경 쇼핑몰 ReBuy의 AI 추천 전문가입니다.
                
                역할:
                - 사용자의 구매 패턴을 분석하여 맞춤형 친환경 상품을 추천합니다.
                - 환경 점수가 높고, 사용자의 선호도와 잘 맞는 상품을 우선 추천합니다.
                - 새로운 카테고리 탐색을 유도하여 다양한 친환경 제품 경험을 제공합니다.
                
                추천 기준:
                1. 사용자가 자주 구매하는 카테고리 우선
                2. 환경 점수 80점 이상 제품 우선
                3. 사용자의 평균 구매 가격대와 비슷한 제품
                4. 구매 이력이 없는 새로운 카테고리도 1-2개 포함
                """;
    }

    private String buildRecommendationUserPrompt(User user, Map<String, Object> pattern, List<Product> products, int limit) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("사용자 정보:\n");
        prompt.append("- 사용자명: ").append(user.getUsername()).append("\n");
        prompt.append("- 총 주문 횟수: ").append(pattern.get("totalOrders")).append("\n");
        prompt.append("- 선호 카테고리: ").append(pattern.get("categoryPreference")).append("\n");
        prompt.append("- 평균 주문 금액: ").append(pattern.get("avgOrderAmount")).append("원\n");
        prompt.append("- 가격대: ").append(pattern.get("priceRange")).append("\n");
        prompt.append("- 평균 환경 점수: ").append(String.format("%.1f", pattern.get("avgEcoScore"))).append("\n");
        prompt.append("- 보유 크레딧: ").append(pattern.get("creditBalance")).append("\n\n");

        prompt.append("추천 가능한 상품 목록:\n");
        for (int i = 0; i < Math.min(products.size(), 30); i++) {
            Product p = products.get(i);
            prompt.append(String.format("[ID: %d] %s - %s - %,d원 - 환경점수: %.1f\n",
                    p.getId(), p.getName(), p.getCategory(), p.getPrice().intValue(), p.getEcoScore()));
        }

        prompt.append("\n위 정보를 바탕으로 ").append(limit).append("개의 상품을 추천하고, ");
        prompt.append("각 상품별로 추천 이유를 간단히 설명해주세요. ");
        prompt.append("그리고 전체적인 추천 인사이트도 한 문장으로 요약해주세요.");

        return prompt.toString();
    }

    private ProductRecommendationResponse parseRecommendationResponse(String aiResponse, List<Product> allProducts) {
        // AI 응답에서 상품 ID 추출 (간단한 파싱)
        List<ProductRecommendationResponse.RecommendedProduct> recommendations = new ArrayList<>();

        // 상품 ID 패턴 찾기: [ID: 숫자]
        String[] lines = aiResponse.split("\n");
        Map<Long, Product> productMap = allProducts.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        for (String line : lines) {
            if (line.contains("ID:") || line.contains("id:")) {
                try {
                    // "ID: 4" 또는 "id: 4" 패턴 추출
                    String[] parts = line.split("ID:|id:");
                    if (parts.length > 1) {
                        String idPart = parts[1].trim().split("[\\]\\s,]")[0];
                        Long productId = Long.parseLong(idPart);

                        Product product = productMap.get(productId);
                        if (product != null && recommendations.stream().noneMatch(r -> r.getProductId().equals(productId))) {
                            recommendations.add(ProductRecommendationResponse.RecommendedProduct.builder()
                                    .productId(product.getId())
                                    .productName(product.getName())
                                    .price(product.getPrice())
                                    .category(product.getCategory().name())
                                    .ecoScore(product.getEcoScore())
                                    .reasonForRecommendation(extractReason(line))
                                    .matchScore(0.9) // 기본값
                                    .build());
                        }
                    }
                } catch (Exception e) {
                    log.debug("상품 ID 파싱 실패: {}", line);
                }
            }
        }

        // AI가 제대로 추천하지 못했을 경우 기본 추천 (환경 점수 높은 순)
        if (recommendations.isEmpty()) {
            recommendations = allProducts.stream()
                    .sorted(Comparator.comparing(Product::getEcoScore).reversed())
                    .limit(5)
                    .map(p -> ProductRecommendationResponse.RecommendedProduct.builder()
                            .productId(p.getId())
                            .productName(p.getName())
                            .price(p.getPrice())
                            .category(p.getCategory().name())
                            .ecoScore(p.getEcoScore())
                            .reasonForRecommendation("환경 점수가 높은 추천 상품입니다.")
                            .matchScore(0.8)
                            .build())
                    .collect(Collectors.toList());
        }

        return ProductRecommendationResponse.builder()
                .recommendations(recommendations)
                .reason("AI 분석 기반 맞춤 추천")
                .aiInsight(extractInsight(aiResponse))
                .build();
    }

    private String extractReason(String line) {
        // 추천 이유 추출 (간단한 버전)
        if (line.contains("-")) {
            String[] parts = line.split("-");
            return parts.length > 1 ? parts[parts.length - 1].trim() : "추천 상품입니다.";
        }
        return "맞춤 추천 상품입니다.";
    }

    private String extractInsight(String aiResponse) {
        // 인사이트 추출 (마지막 문장 또는 요약 부분)
        String[] sentences = aiResponse.split("\\.");
        return sentences.length > 0 ? sentences[sentences.length - 1].trim() : "친환경 소비를 추천드립니다.";
    }

    private List<Order> getMonthlyOrders(User user, YearMonth targetMonth) {
        LocalDate startDate = targetMonth.atDay(1);
        LocalDate endDate = targetMonth.atEndOfMonth();

        return orderRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .filter(order -> {
                    LocalDate orderDate = order.getCreatedAt().toLocalDate();
                    return !orderDate.isBefore(startDate) && !orderDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
    }

    private MonthlyEcoReportResponse.PurchaseStats calculatePurchaseStats(List<Order> orders) {
        int totalOrders = orders.size();
        BigDecimal totalSpent = orders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avgOrderValue = totalOrders > 0
                ? totalSpent.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        Map<String, Integer> categoryDist = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getCategory().name(),
                        Collectors.summingInt(item -> 1)
                ));

        double avgEcoScore = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .mapToDouble(item -> item.getProduct().getEcoScore().doubleValue())
                .average()
                .orElse(0.0);

        return MonthlyEcoReportResponse.PurchaseStats.builder()
                .totalOrders(totalOrders)
                .totalSpent(totalSpent)
                .averageOrderValue(avgOrderValue)
                .categoryDistribution(categoryDist)
                .averageEcoScore(BigDecimal.valueOf(avgEcoScore).setScale(1, RoundingMode.HALF_UP))
                .build();
    }

    private MonthlyEcoReportResponse.EnvironmentalImpact calculateEnvironmentalImpact(List<Order> orders) {
        // 주문한 상품들의 환경 영향 합산
        BigDecimal co2 = BigDecimal.ZERO;
        BigDecimal water = BigDecimal.ZERO;
        BigDecimal oil = BigDecimal.ZERO;
        BigDecimal plastic = BigDecimal.ZERO;

        for (Order order : orders) {
            for (OrderItem item : order.getItems()) {
                Product p = item.getProduct();
                int qty = item.getQuantity();

                if (p.getSavedCo2Kg() != null) co2 = co2.add(p.getSavedCo2Kg().multiply(BigDecimal.valueOf(qty)));
                if (p.getSavedWaterL() != null) water = water.add(p.getSavedWaterL().multiply(BigDecimal.valueOf(qty)));
                if (p.getSavedOilMl() != null) oil = oil.add(p.getSavedOilMl().multiply(BigDecimal.valueOf(qty)));
                if (p.getSavedPlasticG() != null) plastic = plastic.add(p.getSavedPlasticG().multiply(BigDecimal.valueOf(qty)));
            }
        }

        return MonthlyEcoReportResponse.EnvironmentalImpact.builder()
                .co2Saved(co2.setScale(2, RoundingMode.HALF_UP))
                .waterSaved(water.setScale(2, RoundingMode.HALF_UP))
                .oilSaved(oil.setScale(2, RoundingMode.HALF_UP))
                .plasticSaved(plastic.setScale(2, RoundingMode.HALF_UP))
                .equivalentTo("나무 " + co2.divide(BigDecimal.valueOf(10), 0, RoundingMode.DOWN) + "그루를 심은 효과")
                .build();
    }

    private MonthlyEcoReportResponse.RankingInfo calculateRanking(User user, YearMonth targetMonth) {
        // 간단한 순위 계산 (실제로는 더 복잡한 로직 필요)
        List<User> allUsers = userRepository.findAll();

        // 환경 점수 기준 정렬
        List<User> sortedUsers = allUsers.stream()
                .sorted(Comparator.comparing(User::getEnvironmentScore).reversed())
                .collect(Collectors.toList());

        int userRank = sortedUsers.indexOf(user) + 1;
        int totalUsers = allUsers.size();
        double percentile = (double) (totalUsers - userRank + 1) / totalUsers * 100;

        BigDecimal avgScore = BigDecimal.valueOf(
                allUsers.stream()
                        .mapToDouble(u -> u.getEnvironmentScore().doubleValue())
                        .average()
                        .orElse(0.0)
        );

        BigDecimal comparedToAvg = user.getEnvironmentScore()
                .subtract(avgScore)
                .divide(avgScore, 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));

        return MonthlyEcoReportResponse.RankingInfo.builder()
                .userRank(userRank)
                .totalUsers(totalUsers)
                .rankPercentile("상위 " + String.format("%.0f", 100 - percentile) + "%")
                .comparedToAverage(comparedToAvg)
                .build();
    }

    private String buildReportSystemPrompt() {
        return """
                당신은 친환경 쇼핑몰 ReBuy의 녹색 소비 리포트 전문가입니다.
                
                역할:
                - 사용자의 월간 친환경 소비 패턴을 분석합니다.
                - 긍정적인 환경 기여도를 강조하고 격려합니다.
                - 구체적이고 실천 가능한 개선 방안을 제시합니다.
                - 친근하고 동기부여가 되는 톤으로 작성합니다.
                
                리포트 작성 원칙:
                1. 달성한 성과를 먼저 칭찬하기
                2. 숫자로 환경 기여도를 명확히 표현하기
                3. 다음 달 목표를 구체적으로 제시하기
                4. 재미있는 비유나 사례 활용하기
                """;
    }

    private String buildReportUserPrompt(User user,
                                        MonthlyEcoReportResponse.PurchaseStats stats,
                                        MonthlyEcoReportResponse.EnvironmentalImpact impact,
                                        MonthlyEcoReportResponse.RankingInfo ranking,
                                        int year, int month) {
        return String.format("""
                %d년 %d월 녹색 소비 리포트 생성 요청
                
                사용자: %s
                
                구매 통계:
                - 총 주문: %d건
                - 총 지출: %,d원
                - 평균 주문 금액: %,d원
                - 평균 환경 점수: %.1f점
                - 카테고리 분포: %s
                
                환경 기여도:
                - CO2 절감: %.2fkg
                - 물 절약: %.2fL
                - 석유 절약: %.2fml
                - 플라스틱 절감: %.2fg
                
                순위:
                - 전체 %d명 중 %d위 (%s)
                - 평균 대비: %s%%
                
                위 데이터를 바탕으로:
                1. 이번 달 주요 성과 3가지 (achievements)
                2. 다음 달을 위한 추천 행동 3가지 (recommendations)
                3. 전체 소감 및 격려 메시지
                
                를 작성해주세요. 친근하고 동기부여가 되는 톤으로 작성해주세요.
                """,
                year, month,
                user.getUsername(),
                stats.getTotalOrders(),
                stats.getTotalSpent().intValue(),
                stats.getAverageOrderValue().intValue(),
                stats.getAverageEcoScore(),
                stats.getCategoryDistribution(),
                impact.getCo2Saved(),
                impact.getWaterSaved(),
                impact.getOilSaved(),
                impact.getPlasticSaved(),
                ranking.getTotalUsers(),
                ranking.getUserRank(),
                ranking.getRankPercentile(),
                ranking.getComparedToAverage()
        );
    }

    private Map<String, List<String>> parseReportInsights(String aiResponse) {
        Map<String, List<String>> insights = new HashMap<>();
        List<String> achievements = new ArrayList<>();
        List<String> recommendations = new ArrayList<>();

        String[] lines = aiResponse.split("\n");
        String currentSection = "";

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            if (trimmed.contains("성과") || trimmed.contains("achievement") || trimmed.contains("달성")) {
                currentSection = "achievements";
            } else if (trimmed.contains("추천") || trimmed.contains("recommendation") || trimmed.contains("제안")) {
                currentSection = "recommendations";
            } else if (trimmed.matches("^[0-9-•*].+")) {
                // 번호나 불릿으로 시작하는 라인
                String content = trimmed.replaceFirst("^[0-9-•*\\.\\)\\]\\s]+", "").trim();
                if (!content.isEmpty()) {
                    if ("achievements".equals(currentSection)) {
                        achievements.add(content);
                    } else if ("recommendations".equals(currentSection)) {
                        recommendations.add(content);
                    }
                }
            }
        }

        // 기본값 설정
        if (achievements.isEmpty()) {
            achievements.add("친환경 제품 구매로 환경 보호에 기여하셨습니다.");
            achievements.add("지속 가능한 소비 습관을 실천하고 계십니다.");
            achievements.add("녹색 소비자로서 모범을 보이고 계십니다.");
        }

        if (recommendations.isEmpty()) {
            recommendations.add("다양한 카테고리의 친환경 제품을 경험해보세요.");
            recommendations.add("환경 점수 90점 이상 제품에 도전해보세요.");
            recommendations.add("크레딧을 활용하여 더 많은 친환경 제품을 구매해보세요.");
        }

        insights.put("achievements", achievements.stream().limit(3).collect(Collectors.toList()));
        insights.put("recommendations", recommendations.stream().limit(3).collect(Collectors.toList()));

        return insights;
    }

    private MonthlyEcoReportResponse buildEmptyReport(User user, int year, int month) {
        return MonthlyEcoReportResponse.builder()
                .username(user.getUsername())
                .year(year)
                .month(month)
                .reportGeneratedAt(LocalDate.now())
                .purchaseStats(MonthlyEcoReportResponse.PurchaseStats.builder()
                        .totalOrders(0)
                        .totalSpent(BigDecimal.ZERO)
                        .averageOrderValue(BigDecimal.ZERO)
                        .categoryDistribution(new HashMap<>())
                        .averageEcoScore(BigDecimal.ZERO)
                        .build())
                .environmentalImpact(MonthlyEcoReportResponse.EnvironmentalImpact.builder()
                        .co2Saved(BigDecimal.ZERO)
                        .waterSaved(BigDecimal.ZERO)
                        .oilSaved(BigDecimal.ZERO)
                        .plasticSaved(BigDecimal.ZERO)
                        .equivalentTo("아직 기록이 없습니다")
                        .build())
                .aiAnalysis(String.format("%d년 %d월에는 구매 기록이 없습니다. 친환경 제품을 구매하여 녹색 소비를 시작해보세요!", year, month))
                .achievements(List.of("새로운 시작을 준비하고 계시네요!"))
                .recommendations(List.of(
                        "첫 친환경 제품 구매로 녹색 소비를 시작해보세요.",
                        "환경 점수 80점 이상 제품을 추천드립니다.",
                        "생활용품부터 시작하면 쉽게 친환경 생활을 실천할 수 있습니다."
                ))
                .ranking(MonthlyEcoReportResponse.RankingInfo.builder()
                        .userRank(0)
                        .totalUsers(0)
                        .rankPercentile("순위 집계 중")
                        .comparedToAverage(BigDecimal.ZERO)
                        .build())
                .build();
    }
}

