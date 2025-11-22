package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import com.rebuy.entity.enums.ParticipationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participations",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_activity",
                        columnNames = {"user_id", "activity_id"}
                )
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Participation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 누가 참여했는지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 어떤 활동인지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private EnvironmentalActivity activity;

    // 현재 상태 (신청 -> 인증대기 -> 완료/반려)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private ParticipationStatus status = ParticipationStatus.APPLIED;

    // 인증샷 이미지 URL (나중에 유저가 업로드)
    @Column(length = 500)
    private String proofImageUrl;

    // 보상 지급 여부 (상태가 VERIFIED여도 중복 지급 방지용으로 체크)
    @Builder.Default
    private boolean isRewardGiven = false;

    // --- 비즈니스 편의 메서드 ---

    // 인증샷 업로드 (상태 변경)
    public void uploadProof(String imageUrl) {
        this.proofImageUrl = imageUrl;
        this.status = ParticipationStatus.PENDING_VERIFICATION;
    }

    // 관리자 승인 (보상 지급 처리용)
    public void confirmVerification() {
        this.status = ParticipationStatus.VERIFIED;
        this.isRewardGiven = true;
    }

    // 관리자 반려
    public void rejectVerification() {
        this.status = ParticipationStatus.REJECTED;
    }
}