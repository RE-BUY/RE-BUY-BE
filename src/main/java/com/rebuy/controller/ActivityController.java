package com.rebuy.controller;

import com.rebuy.service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
@CrossOrigin(
    origins = {
        "http://localhost:8080",
        "http://localhost:3000",
        "http://localhost:5173",
        "http://127.0.0.1:8080",
        "http://127.0.0.1:3000",
        "http://127.0.0.1:5173",
        "http://192.168.45.170:8080",
        "http://192.168.45.170:3000",
        "http://192.168.45.170:5173"
    },
    allowCredentials = "true",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
@Tag(name = "Environmental Activity", description = "환경 활동(플로깅) 관련 API")
@SecurityRequirement(name = "bearerAuth")
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping
    @Operation(summary = "활동 목록 조회", description = "모든 환경 활동 목록을 조회합니다.")
    public ResponseEntity<?> getActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    @GetMapping("/{activityId}")
    @Operation(summary = "활동 상세 조회", description = "특정 활동의 상세 정보를 조회합니다.")
    public ResponseEntity<?> getActivity(@PathVariable Long activityId) {
        return ResponseEntity.ok(activityService.getActivity(activityId));
    }

    @PostMapping("/{activityId}/apply")
    @Operation(summary = "활동 참여 신청", description = "로그인한 사용자가 특정 활동에 참여를 신청합니다.")
    public ResponseEntity<String> apply(
            @PathVariable Long activityId,
            @AuthenticationPrincipal UserDetails userDetails // 토큰에서 정보 추출
    ) {
        String username = userDetails.getUsername();

        activityService.applyForActivity(username, activityId);

        return ResponseEntity.ok("참여 신청이 완료되었습니다.");
    }

    @GetMapping("/{activityId}/check-application")
    @Operation(summary = "활동 신청 여부 확인", description = "로그인한 사용자가 특정 활동에 신청했는지 확인합니다. 비로그인 시 false 반환.")
    public ResponseEntity<?> checkApplication(
            @PathVariable Long activityId,
            @AuthenticationPrincipal(errorOnInvalidType = false) UserDetails userDetails
    ) {
        // 로그인하지 않은 경우
        if (userDetails == null) {
            return ResponseEntity.ok(java.util.Map.of(
                "activityId", activityId,
                "isApplied", false
            ));
        }

        String username = userDetails.getUsername();
        boolean isApplied = activityService.checkIfApplied(username, activityId);

        return ResponseEntity.ok(java.util.Map.of(
            "activityId", activityId,
            "isApplied", isApplied
        ));
    }

    @GetMapping("/my-applications")
    @Operation(summary = "내 신청 목록 조회", description = "로그인한 사용자가 신청한 모든 활동 목록을 조회합니다.")
    public ResponseEntity<?> getMyApplications(
            @AuthenticationPrincipal(errorOnInvalidType = false) UserDetails userDetails
    ) {
        // 로그인하지 않은 경우
        if (userDetails == null) {
            return ResponseEntity.ok(java.util.Collections.emptyList());
        }

        String username = userDetails.getUsername();
        return ResponseEntity.ok(activityService.getMyApplications(username));
    }
    // 1. [사용자] 인증샷 제출 API
    @PostMapping("/participations/{participationId}/verify")
    @Operation(summary = "활동 인증샷 제출", description = "참여 ID와 이미지 URL을 보냅니다.")
    public ResponseEntity<String> verifyActivity(
            @PathVariable Long participationId,
            @RequestParam String imageUrl
    ) {
        activityService.requestVerification(participationId, imageUrl);
        return ResponseEntity.ok("인증샷이 제출되었습니다. 관리자 승인을 기다리세요.");
    }

    // 2. [관리자] 인증 승인 및 보상 지급 API
    @PostMapping("/admin/participations/{participationId}/confirm")
    @Operation(summary = "[관리자] 인증 승인 및 보상 지급", description = "해당 참여 내역을 승인하고 유저에게 크레딧을 지급합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> confirmReward(
            @PathVariable Long participationId
    ) {
        activityService.confirmReward(participationId);
        return ResponseEntity.ok("승인 완료! 사용자에게 크레딧이 지급되었습니다.");
    }
}