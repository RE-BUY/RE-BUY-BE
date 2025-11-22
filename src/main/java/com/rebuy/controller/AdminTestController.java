package com.rebuy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")
public class AdminTestController {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminOnly() {
        return "관리자님 환영합니다!";
    }
    @GetMapping("/check")
    public Object checkMyBadge() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
