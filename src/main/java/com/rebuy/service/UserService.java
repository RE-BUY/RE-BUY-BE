package com.rebuy.service;

import com.rebuy.controller.dto.user.UserRegistrationRequest;
import com.rebuy.controller.dto.user.UserResponse;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.Role;
import com.rebuy.global.exception.DuplicateResourceException;
import com.rebuy.repository.UserRepository;
import com.rebuy.global.util.PasswordPolicyValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register(UserRegistrationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("이미 사용 중인 사용자명입니다.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 정책 검증
        PasswordPolicyValidator.validate(request.getPassword());

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .environmentScore(BigDecimal.ZERO)
                .creditBalance(BigDecimal.ZERO)
                .emailVerified(false)
                .build();

        user.getRoles().add(Role.USER);

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .creditBalance(user.getCreditBalance())
                .environmentScore(user.getEnvironmentScore())
                .build();
    }
}