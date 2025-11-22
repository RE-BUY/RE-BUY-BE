package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import com.rebuy.entity.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 브랜드 (예: FREITAG)
    @Column(nullable = false)
    private String brand;

    // 상품명 (예: 리사이클링 지갑)
    @Column(nullable = false)
    private String name;

    // 카테고리 (예: FASHION)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    // 상세 카테고리 (예: "지갑", "크로스백" - 필요 시 사용)
    private String subCategory;

    // 모델 번호 (예: F257 SUTTON...)
    private String modelNumber;

    // 가격
    @Column(nullable = false)
    private int price;

    // 재고
    @Column(nullable = false)
    private int stock;

    // 환경 점수 (기존 요구사항 유지)
    @Column(precision = 10, scale = 4)
    private BigDecimal ecoScore;

    // 설명 텍스트
    @Column(columnDefinition = "TEXT")
    private String description;

    // 연관된 이미지들 (OneToMany로 관리)
    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    // 연관된 리뷰들
    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}