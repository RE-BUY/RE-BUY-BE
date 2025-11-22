package com.rebuy.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailVerificationResponse {
    private boolean verified;
    private String message;
}