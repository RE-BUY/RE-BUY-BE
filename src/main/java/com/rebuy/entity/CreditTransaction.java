package com.rebuy.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_transactions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreditTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(name = "balance_after", precision = 18, scale = 4, nullable = false)
    private BigDecimal balanceAfter;

    @Column(length = 40, nullable = false)
    private String type;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private LocalDateTime occurredAt;

    @PrePersist
    void onPersist() {
        if (occurredAt == null) {
            occurredAt = LocalDateTime.now();
        }
    }
}