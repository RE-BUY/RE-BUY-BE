package com.rebuy.global.exception.config;

import com.rebuy.entity.User;
import com.rebuy.entity.enums.Role;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Order(2)  // AdminInitializer 다음에 실행
public class TestUserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // admin2 테스트 계정 생성
        if (!userRepository.existsByUsername("admin2")) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_USER);
            roles.add(Role.ROLE_ADMIN);

            User admin2 = User.builder()
                    .username("admin2")
                    .password(passwordEncoder.encode("Admin123!"))
                    .email("admin2@rebuy.com")
                    .phone("010-1234-5678")
                    .roles(roles)
                    .creditBalance(BigDecimal.valueOf(10000))
                    .environmentScore(BigDecimal.valueOf(100))
                    .totalSavedCo2Kg(BigDecimal.ZERO)
                    .totalSavedWaterL(BigDecimal.ZERO)
                    .totalSavedOilMl(BigDecimal.ZERO)
                    .totalSavedPlasticG(BigDecimal.ZERO)
                    .build();

            userRepository.save(admin2);
            System.out.println("=========== 테스트 계정 생성 완료 (ID: admin2 / PW: Admin123!) ===========");
        } else {
            System.out.println(">>> admin2 계정이 이미 있습니다.");
        }

        // 일반 사용자 테스트 계정
        if (!userRepository.existsByUsername("user1")) {
            User user1 = User.builder()
                    .username("user1")
                    .password(passwordEncoder.encode("User123!"))
                    .email("user1@rebuy.com")
                    .phone("010-9999-8888")
                    .roles(Set.of(Role.ROLE_USER))
                    .creditBalance(BigDecimal.valueOf(5000))
                    .environmentScore(BigDecimal.valueOf(50))
                    .totalSavedCo2Kg(BigDecimal.ZERO)
                    .totalSavedWaterL(BigDecimal.ZERO)
                    .totalSavedOilMl(BigDecimal.ZERO)
                    .totalSavedPlasticG(BigDecimal.ZERO)
                    .build();

            userRepository.save(user1);
            System.out.println("=========== 일반 사용자 계정 생성 완료 (ID: user1 / PW: User123!) ===========");
        }
    }
}

