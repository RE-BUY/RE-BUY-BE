package com.rebuy.dto.product;

import com.rebuy.entity.Review;
import java.time.format.DateTimeFormatter;

public record ReviewResponse(
        Long reviewId,
        String writerName, // 작성자 이름 (개인정보 보호를 위해 마스킹 처리 가능)
        int rating,
        String content,
        String createdAt   // "2024.01.20" 형식
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUser().getUsername(), // 혹은 닉네임
                review.getRating(),
                review.getContent(),
                review.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy. MM. dd."))
        );
    }
}