package com.rebuy.service;

import com.rebuy.entity.EmailVerificationToken;
import com.rebuy.entity.PasswordResetToken;
import com.rebuy.entity.User;
import com.rebuy.repository.EmailVerificationTokenRepository;
import com.rebuy.repository.PasswordResetTokenRepository;
import com.rebuy.repository.UserRepository;
import com.rebuy.util.PasswordPolicyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthExtensionService {

    private final UserRepository userRepository;
    private final EmailVerificationTokenRepository emailVerificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    // 이메일 인증 토큰 생성
    public void generateEmailVerification(User user) {
        String token = UUID.randomUUID().toString().replace("-", "");
        EmailVerificationToken evt = EmailVerificationToken.builder()
                .token(token)
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(24))
                .used(false)
                .build();
        emailVerificationTokenRepository.save(evt);
        mailService.sendEmailVerification(user.getEmail(), token);
    }

    // 이메일 인증 처리
    public boolean verifyEmail(String token) {
        EmailVerificationToken evt = emailVerificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("토큰이 유효하지 않습니다."));
        if (evt.isUsed() || evt.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("토큰이 만료되었거나 이미 사용되었습니다.");
        }
        User user = evt.getUser();
        user.setEmailVerified(true);
        evt.setUsed(true);
        userRepository.save(user);
        emailVerificationTokenRepository.save(evt);
        return true;
    }

    // 비밀번호 재설정 요청
    public void requestPasswordReset(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일 사용자가 없습니다."));
        String token = UUID.randomUUID().toString().replace("-", "");
        PasswordResetToken prt = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(2))
                .used(false)
                .build();
        passwordResetTokenRepository.save(prt);
        mailService.sendPasswordReset(email, token);
    }

    // 비밀번호 변경 확정
    public void changePassword(String token, String newPassword) {
        PasswordPolicyValidator.validate(newPassword); // 정책 검사
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("토큰이 유효하지 않습니다."));
        if (prt.isUsed() || prt.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("토큰이 만료되었거나 이미 사용되었습니다.");
        }
        User user = prt.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        prt.setUsed(true);
        userRepository.save(user);
        passwordResetTokenRepository.save(prt);
    }
}