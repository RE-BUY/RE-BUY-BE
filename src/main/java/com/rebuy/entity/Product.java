package com.rebuy.entity;

import com.rebuy.global.base.BaseTimeEntity;
import com.rebuy.entity.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ProductCategory category;

    @Column(length = 255)
    private String description;

    @Column(length = 120)
    private String manufacturer;

    @Column(length = 255)
    private String imageUrl;

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(precision = 18, scale = 4)
    private BigDecimal savedCo2Kg;

    @Column(precision = 18, scale = 4)
    private BigDecimal savedWaterL;

    @Column(precision = 18, scale = 4)
    private BigDecimal savedOilMl;

    @Column(precision = 18, scale = 4)
    private BigDecimal savedPlasticG;

    @Column(precision = 18, scale = 4)
    private BigDecimal ecoScore;

    @Column(precision = 18, scale = 4)
    private BigDecimal ecoBaseScore;
}