package com.rebuy.dto.mypage;

import java.math.BigDecimal;

public record EcoStatDto(
        String yearMonth,   // "2024-01"
        BigDecimal score
) {}