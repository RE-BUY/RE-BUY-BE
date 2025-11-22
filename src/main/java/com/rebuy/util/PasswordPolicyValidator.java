package com.rebuy.util;

public final class PasswordPolicyValidator {

    // 최소 8자, 대문자/소문자/숫자/특수문자(!@#$%^&*) 포함
    private static final String POLICY_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$";

    private PasswordPolicyValidator() {}

    public static void validate(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("비밀번호가 비어 있습니다.");
        }
        if (!rawPassword.matches(POLICY_REGEX)) {
            throw new IllegalArgumentException("비밀번호 정책 불일치: 최소 8자, 대문자/소문자/숫자/특수문자(!@#$%^&*) 포함해야 합니다.");
        }
    }
}