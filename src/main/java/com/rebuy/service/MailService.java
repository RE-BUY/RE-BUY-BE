package com.rebuy.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    public void sendEmailVerification(String email, String token) {
        // 올바른 경로 (auth 포함)
        log.info("[MAIL] 이메일 인증 링크: http://localhost:8080/api/v1/auth/verify-email?token={}", token);
    }

    public void sendPasswordReset(String email, String token) {
        // 올바른 경로 (auth 포함). 실제 비밀번호 변경은 POST 필요
        log.info("[MAIL] 비밀번호 재설정 링크(토큰 확인): http://localhost:8080/api/v1/auth/password-reset/confirm?token={}", token);
        log.info("[MAIL] 비밀번호 재설정 POST 예시: curl -X POST http://localhost:8080/api/v1/auth/password-reset/confirm "
                + "-H 'Content-Type: application/json' -d '{\"token\":\"" + token + "\", \"newPassword\":\"NewPass999!\"}'");
    }
}