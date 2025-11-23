package com.rebuy.service;

import com.rebuy.entity.EnvironmentalActivity;
import com.rebuy.entity.Participation;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.ParticipationStatus;
import com.rebuy.global.exception.ResourceNotFoundException;
import com.rebuy.repository.EnvironmentalActivityRepository;
import com.rebuy.repository.ParticipationRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityService {

    private final EnvironmentalActivityRepository activityRepository;
    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;


    public java.util.List<EnvironmentalActivity> getAllActivities() {
        return activityRepository.findAll();
    }


    public EnvironmentalActivity getActivity(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 활동을 찾을 수 없습니다."));
    }

    @Transactional
    public Long applyForActivity(String username, Long activityId) {
        log.debug("applyForActivity called: username={} activityId={}", username, activityId);

        // 1. 사용자 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다: " + username));

        // 2. 활동 조회
        EnvironmentalActivity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 활동을 찾을 수 없습니다."));

        // 3. 중복 신청 검증
        if (participationRepository.existsByUserAndActivity(user, activity)) {
            throw new IllegalStateException("이미 신청한 활동입니다.");
        }

        // 4. 인원 제한 검증
        if (activity.getParticipantLimit() != null) {
            int currentCount = participationRepository.countByActivity(activity);
            if (currentCount >= activity.getParticipantLimit()) {
                throw new IllegalStateException("모집 인원이 마감되었습니다.");
            }
        }

        // 5. 참여 내역 저장
        Participation participation = Participation.builder()
                .user(user)
                .activity(activity)
                .status(ParticipationStatus.APPLIED)
                .build();

        participationRepository.save(participation);

        return participation.getId();
    }

    public boolean checkIfApplied(String username, Long activityId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다: " + username));

        EnvironmentalActivity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 활동을 찾을 수 없습니다."));

        return participationRepository.existsByUserAndActivity(user, activity);
    }

    public java.util.List<ParticipationDto> getMyApplications(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("사용자를 찾을 수 없습니다: " + username));

        java.util.List<Participation> participations = participationRepository.findByUserOrderByCreatedAtDesc(user);

        return participations.stream()
                .map(p -> ParticipationDto.builder()
                        .participationId(p.getId())
                        .activityId(p.getActivity().getId())
                        .activityName(p.getActivity().getName())
                        .activityDescription(p.getActivity().getDescription())
                        .startAt(p.getActivity().getStartAt())
                        .endAt(p.getActivity().getEndAt())
                        .status(p.getStatus().name())
                        .proofImageUrl(p.getProofImageUrl())
                        .rewardGiven(p.isRewardGiven())
                        .appliedAt(p.getCreatedAt())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    @lombok.Builder
    @lombok.Getter
    @lombok.AllArgsConstructor
    @lombok.NoArgsConstructor
    public static class ParticipationDto {
        private Long participationId;
        private Long activityId;
        private String activityName;
        private String activityDescription;
        private java.time.LocalDateTime startAt;
        private java.time.LocalDateTime endAt;
        private String status;
        private String proofImageUrl;
        private boolean rewardGiven;
        private java.time.LocalDateTime appliedAt;
    }

    @Transactional
    public void requestVerification(Long participationId, String imageUrl) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new ResourceNotFoundException("참여 내역이 없습니다."));

        // 상태 변경: 신청됨(APPLIED) -> 인증대기(PENDING)
        participation.uploadProof(imageUrl);
    }
    @Transactional
    public void confirmReward(Long participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new ResourceNotFoundException("참여 내역이 없습니다."));

        // 이미 지급됐는지 확인
        if (participation.isRewardGiven()) {
            throw new IllegalStateException("이미 보상이 지급되었습니다.");
        }

        // 1. 상태 변경: 인증대기 -> 완료(VERIFIED)
        participation.confirmVerification();

        // 2. 유저에게 크레딧 지급 (활동에 설정된 보상금액만큼)
        java.math.BigDecimal rewardAmount = java.math.BigDecimal.valueOf(1000); // 예: 1000포인트 고정 (또는 activity.getReward() 사용)

        participation.getUser().addCredit(rewardAmount);
    }
}