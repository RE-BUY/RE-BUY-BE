package com.rebuy.controller;

import com.rebuy.controller.dto.ai.MonthlyEcoReportResponse;
import com.rebuy.controller.dto.ai.ProductRecommendationResponse;
import com.rebuy.service.AIRecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
@Tag(name = "AI Recommendation", description = "AI 기반 상품 추천 및 리포트 API")
@SecurityRequirement(name = "bearerAuth")
public class AIRecommendationController {

    private final AIRecommendationService aiRecommendationService;

    @GetMapping("/recommendations")
    @Operation(summary = "AI 상품 추천",
               description = "Upstage Solar-Pro2 기반 사용자 맞춤형 상품 추천을 제공합니다.")
    public ResponseEntity<ProductRecommendationResponse> getRecommendations(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "5") int limit
    ) {
        String username = userDetails.getUsername();
        ProductRecommendationResponse recommendations =
                aiRecommendationService.recommendProducts(username, limit);

        return ResponseEntity.ok(recommendations);
    }

    @GetMapping("/report/monthly")
    @Operation(summary = "월별 녹색 소비 리포트",
               description = "AI 분석을 통한 월별 환경 기여도 리포트를 생성합니다.")
    public ResponseEntity<MonthlyEcoReportResponse> getMonthlyReport(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        String username = userDetails.getUsername();

        LocalDate now = LocalDate.now();
        int targetYear = year != null ? year : now.getYear();
        int targetMonth = month != null ? month : now.getMonthValue();

        MonthlyEcoReportResponse report =
                aiRecommendationService.generateMonthlyReport(username, targetYear, targetMonth);

        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/current")
    @Operation(summary = "현재 월 녹색 소비 리포트",
               description = "현재 진행 중인 달의 녹색 소비 리포트를 생성합니다.")
    public ResponseEntity<MonthlyEcoReportResponse> getCurrentMonthReport(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername();
        LocalDate now = LocalDate.now();

        MonthlyEcoReportResponse report =
                aiRecommendationService.generateMonthlyReport(username, now.getYear(), now.getMonthValue());

        return ResponseEntity.ok(report);
    }
}

