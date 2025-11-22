package com.rebuy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "environmental_impacts")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EnvironmentalImpact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연결된 제품 (1:1 가정)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    // 탄소 배출량(g CO2e)
    @Column(precision = 18, scale = 6)
    private BigDecimal carbonEmission;

    // 물 사용량(L)
    @Column(precision = 18, scale = 6)
    private BigDecimal waterUsage;

    // 폐기물 발생량(g)
    @Column(precision = 18, scale = 6)
    private BigDecimal wasteGenerated;
}