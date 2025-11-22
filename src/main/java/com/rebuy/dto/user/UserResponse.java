package com.rebuy.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private BigDecimal creditBalance;
    private BigDecimal environmentScore;
}