package com.rebuy.config;

import com.rebuy.entity.User;
import com.rebuy.entity.enums.Role;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // BCryptPasswordEncoder 등

    @Override
    public void run(String... args) throws Exception {
        // 1. 이미 관리자 계정이 있는지 확인 (중복 생성 방지)
        if (userRepository.existsByUsername("admin")) { // 여기 바꿈
            System.out.println(">>> admin 계정이 이미 있습니다.");
            return;
        }

        User admin = User.builder()
                .username("admin") // ★ 여기 바꿈 (이제 내 아이디는 admin)
                .password(passwordEncoder.encode("admin1234"))
                .email("admin@rebuy.com") //
                .phone("010-0000-0000")
                .roles(Collections.singleton(Role.ADMIN)) // ★ ADMIN 권한 부여
                .build();

        userRepository.save(admin);
        System.out.println("=========== 초기 관리자 계정 생성 완료 (ID: admin / PW: admin1234) ===========");
    }
}