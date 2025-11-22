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
@Tag(name = "Environmental Activity", description = "환경 활동(플로깅) 관련 API")
@SecurityRequirement(name = "bearerAuth") // 자물쇠 필수!
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/{activityId}/apply")
    @Operation(summary = "활동 참여 신청", description = "로그인한 사용자가 특정 활동에 참여를 신청합니다.")
    public ResponseEntity<String> apply(
            @PathVariable Long activityId,
            @AuthenticationPrincipal UserDetails userDetails // 토큰에서 정보 추출
    ) {
        // 토큰에 있는 username ("master" 등)을 그대로 서비스에 넘김
        String username = userDetails.getUsername();

        activityService.applyForActivity(username, activityId);

        return ResponseEntity.ok("참여 신청이 완료되었습니다.");
    }
    // 1. [사용자] 인증샷 제출 API
// (원래는 MultipartFile을 받아야 하지만, 테스트를 위해 String URL로 대체합니다)
    @PostMapping("/participations/{participationId}/verify")
    @Operation(summary = "활동 인증샷 제출", description = "참여 ID와 이미지 URL을 보냅니다.")
    public ResponseEntity<String> verifyActivity(
            @PathVariable Long participationId,
            @RequestParam String imageUrl // 가짜 이미지 주소 (예: "http://my-image.com/photo.jpg")
    ) {
        activityService.requestVerification(participationId, imageUrl);
        return ResponseEntity.ok("인증샷이 제출되었습니다. 관리자 승인을 기다리세요.");
    }

    // 2. [관리자] 인증 승인 및 보상 지급 API
    @PostMapping("/admin/participations/{participationId}/confirm")
    @Operation(summary = "[관리자] 인증 승인 및 보상 지급", description = "해당 참여 내역을 승인하고 유저에게 크레딧을 지급합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // ★ 관리자만 가능!
    public ResponseEntity<String> confirmReward(
            @PathVariable Long participationId
    ) {
        activityService.confirmReward(participationId);
        return ResponseEntity.ok("승인 완료! 사용자에게 크레딧이 지급되었습니다.");
    }
}