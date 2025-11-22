package com.rebuy.controller;

import com.rebuy.dto.mypage.MyPageResponse;
import com.rebuy.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails; // 또는 커스텀 UserDetails
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Tag(name = "MyPage", description = "마이페이지 관련 API")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping
    @Operation(summary = "마이페이지 메인 정보 조회", description = "프로필, 포인트, 소나무, 최근주문, 통계 그래프 데이터를 반환합니다.")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        // JWT 인증을 통해 userDetails에서 ID를 추출한다고 가정
        // 실제 구현 시에는 UserDetails 구현체에 따라 id를 가져오는 방식이 다를 수 있음
        Long userId = Long.parseLong(userDetails.getUsername());

        MyPageResponse response = myPageService.getMyPageData(userId);
        return ResponseEntity.ok(response);
    }
}