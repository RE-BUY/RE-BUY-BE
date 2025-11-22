package com.rebuy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/api/admin")
// ★ 아래 줄을 추가해서 자물쇠를 달아주세요!
// (주의: name 부분은 Swagger 설정파일에 적힌 이름이어야 하는데, 보통 "Bearer Authentication" 아니면 "Authorization" 입니다.)
@SecurityRequirement(name = "bearerAuth")
public class AdminTestController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminOnly() {
        return "관리자님 환영합니다!";
    }
    @GetMapping("/check")
    public Object checkMyBadge() {
        // 현재 로그인한 사용자의 "권한 목록"을 그대로 리턴
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
