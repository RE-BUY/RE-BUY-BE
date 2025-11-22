package com.rebuy.entity.enums;

public enum OrderStatus {
    PENDING,      // 생성 후 결제 대기
    PAID,         // 결제 완료
    CANCELLED,    // 취소
    COMPLETED     // 배송/수령 완료
}