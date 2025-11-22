package com.rebuy.entity.enums;

public enum ParticipationStatus {
    APPLIED,              // 1. 신청 완료 (활동 전)
    PENDING_VERIFICATION, // 2. 인증샷 업로드 완료 (관리자 승인 대기)
    VERIFIED,             // 3. 인증 승인됨 (보상 지급 완료)
    REJECTED,             // 4. 인증 반려됨 (사진 부적절 등)
    CANCELLED             // 5. 사용자 취소
}