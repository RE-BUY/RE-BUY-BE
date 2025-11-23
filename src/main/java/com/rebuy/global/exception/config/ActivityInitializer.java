package com.rebuy.global.exception.config;

import com.rebuy.entity.EnvironmentalActivity;
import com.rebuy.repository.EnvironmentalActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Order(4)  // ProductInitializer 다음에 실행
public class ActivityInitializer implements CommandLineRunner {

    private final EnvironmentalActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        if (activityRepository.count() > 0) {
            System.out.println(">>> 플로깅 활동 데이터가 이미 있습니다.");
            return;
        }

        // 대구 지역 플로깅 활동 5개 생성
        activityRepository.save(EnvironmentalActivity.builder()
                .name("대구 두류공원 플로깅")
                .description("두류공원 일대에서 쓰레기를 줍는 환경 보호 활동입니다. 편한 복장과 장갑을 지참해주세요.")
                .startAt(LocalDateTime.now().plusDays(3))
                .endAt(LocalDateTime.now().plusDays(3).plusHours(2))
                .participantLimit(30)
                .build());

        activityRepository.save(EnvironmentalActivity.builder()
                .name("대구 수성못 생태 정화 활동")
                .description("수성못 주변 산책로와 호숫가 쓰레기 수거 활동입니다. 환경을 사랑하는 시민 여러분의 참여를 기다립니다.")
                .startAt(LocalDateTime.now().plusDays(5))
                .endAt(LocalDateTime.now().plusDays(5).plusHours(3))
                .participantLimit(25)
                .build());

        activityRepository.save(EnvironmentalActivity.builder()
                .name("대구 달성공원 클린업 데이")
                .description("달성공원 내 녹지 지역 정화 활동입니다. 가족 단위 참가 환영합니다. 쓰레기봉투와 집게는 현장에서 제공됩니다.")
                .startAt(LocalDateTime.now().plusDays(7))
                .endAt(LocalDateTime.now().plusDays(7).plusHours(2))
                .participantLimit(40)
                .build());

        activityRepository.save(EnvironmentalActivity.builder()
                .name("대구 금호강변 플로깅 런")
                .description("금호강 자전거길을 따라 조깅하며 쓰레기를 줍는 플로깅 런입니다. 운동화와 편한 복장 착용 필수!")
                .startAt(LocalDateTime.now().plusDays(10))
                .endAt(LocalDateTime.now().plusDays(10).plusHours(2))
                .participantLimit(20)
                .build());

        activityRepository.save(EnvironmentalActivity.builder()
                .name("대구 앞산 등산로 정화 활동")
                .description("앞산 등산로 주변 쓰레기 수거 및 환경 보호 캠페인입니다. 등산 가능한 체력이 필요합니다.")
                .startAt(LocalDateTime.now().plusDays(14))
                .endAt(LocalDateTime.now().plusDays(14).plusHours(4))
                .participantLimit(35)
                .build());

        System.out.println("=========== 대구 지역 플로깅 활동 5개 생성 완료 ===========");
    }
}

