package com.rebuy.controller;

import com.rebuy.dto.auth.EmailVerificationResponse;
import com.rebuy.dto.auth.LoginRequest;
import com.rebuy.dto.auth.LoginResponse;
import com.rebuy.dto.auth.PasswordChangeRequest;
import com.rebuy.dto.auth.PasswordResetRequest;
import com.rebuy.dto.user.UserRegistrationRequest;
import com.rebuy.dto.user.UserResponse;
import com.rebuy.entity.User;
import com.rebuy.repository.UserRepository;
import com.rebuy.security.JwtTokenProvider;
import com.rebuy.service.AuthExtensionService;
import com.rebuy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthExtensionService authExtensionService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRegistrationRequest request) {
        UserResponse resp = userService.register(request);

        userRepository.findByUsername(resp.getUsername()).ifPresent(authExtensionService::generateEmailVerification);
        return resp;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User u = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));

        if (!u.isEmailVerified()) {
            throw new IllegalStateException("이메일 미인증 사용자입니다.");
        }

        String accessToken = jwtTokenProvider.generateToken(auth);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(jwtTokenProvider.getExpirationMillis())
                .build();
    }

    @GetMapping("/verify-email")
    public EmailVerificationResponse verifyEmail(@RequestParam String token) {
        boolean ok = authExtensionService.verifyEmail(token);
        return EmailVerificationResponse.builder()
                .verified(ok)
                .message(ok ? "이메일 인증 완료" : "실패")
                .build();
    }

    @PostMapping("/password-reset/request")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requestPasswordReset(@Valid @RequestBody PasswordResetRequest request) {
        authExtensionService.requestPasswordReset(request.getEmail());
    }

    @PostMapping("/password-reset/confirm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmPasswordReset(@Valid @RequestBody PasswordChangeRequest request) {
        authExtensionService.changePassword(request.getToken(), request.getNewPassword());
    }
}