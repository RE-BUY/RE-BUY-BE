package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import com.rebuy.entity.enums.Role;
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
}