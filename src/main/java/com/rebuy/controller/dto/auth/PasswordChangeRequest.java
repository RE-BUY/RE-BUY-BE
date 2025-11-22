package com.rebuy.controller.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordChangeRequest {
    @NotBlank
    private String token;
    @NotBlank
    private String newPassword;
}