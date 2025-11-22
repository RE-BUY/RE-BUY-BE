package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 대상 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 별점 (1~5)
    @Column(nullable = false)
    private int rating;

    // 내용
    @Column(nullable = false, length = 1000)
    private String content;

    // 리뷰 이미지 (필요시 추가)
    @Column(length = 500)
    private String reviewImageUrl;
}