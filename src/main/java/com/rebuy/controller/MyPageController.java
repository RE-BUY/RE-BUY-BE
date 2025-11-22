package com.rebuy.controller;

import com.rebuy.controller.dto.mypage.MyPageResponse;
import com.rebuy.entity.User;
import com.rebuy.repository.UserRepository;
import com.rebuy.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Tag(name = "MyPage", description = "마이페이지 관련 API")
public class MyPageController {

    private final MyPageService myPageService;
    private final UserRepository userRepository;

    @GetMapping
    @Operation(summary = "마이페이지 메인 정보 조회", description = "프로필, 포인트, 소나무, 최근주문, 통계 그래프 데이터를 반환합니다.")
    public ResponseEntity<MyPageResponse> getMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        // username을 통해 User 조회
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));

        // 조회한 User의 id를 사용하여 서비스 호출
        MyPageResponse response = myPageService.getMyPageData(user.getId());
        return ResponseEntity.ok(response);
    }
}