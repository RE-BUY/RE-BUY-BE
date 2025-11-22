package com.rebuy.controller;

import com.rebuy.entity.EnvironmentalActivity;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.Role;
import com.rebuy.repository.EnvironmentalActivityRepository; // ★ 추가
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class SetupController {

    private final UserRepository userRepository;
    private final EnvironmentalActivityRepository activityRepository; // ★ 추가
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/setup-activity")
    public String setupActivity() {
        try {
            EnvironmentalActivity activity = EnvironmentalActivity.builder()
                    .name("한강 플로깅 모임")
                    .description("이번 주말 한강에서 쓰레기 같이 주워요! 봉투 제공합니다.")
                    .participantLimit(5) // 5명 선착순
                    .startAt(LocalDateTime.now().plusDays(3))
                    .endAt(LocalDateTime.now().plusDays(3).plusHours(2))
                    .build();

            activityRepository.save(activity);

            return "<h1>활동 데이터 생성 성공!</h1>" +
                    "<p>활동명: " + activity.getName() + "</p>" +
                    "<p>ID: <b>" + activity.getId() + "</b> (이 번호를 기억하세요!)</p>";

        } catch (Exception e) {
            return "<h1>실패...</h1>" + e.getMessage();
        }
    }
}