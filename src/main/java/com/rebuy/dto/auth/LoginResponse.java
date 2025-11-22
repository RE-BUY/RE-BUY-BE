package com.rebuy.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String tokenType; // Bearer
    private long expiresIn;   // ms
}