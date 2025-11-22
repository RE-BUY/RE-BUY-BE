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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(precision = 18, scale = 6)
    private BigDecimal carbonEmission;

    @Column(precision = 18, scale = 6)
    private BigDecimal waterUsage;

    @Column(precision = 18, scale = 6)
    private BigDecimal wasteGenerated;
}