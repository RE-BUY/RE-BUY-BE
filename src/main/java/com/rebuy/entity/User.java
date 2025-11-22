package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 120)
    private String email;

    @Column(length = 20)
    private String phone;

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal environmentScore = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal creditBalance = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private boolean emailVerified = false;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", length = 40, nullable = false)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalSavedCo2Kg = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalSavedWaterL = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalSavedOilMl = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalSavedPlasticG = BigDecimal.ZERO;
}