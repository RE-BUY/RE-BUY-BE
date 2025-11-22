package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
import com.rebuy.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus status;

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalAmount;          // 상품 총액(할인/크레딧 적용 전)

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalCreditUsed;      // 사용한 크레딧

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal totalCreditEarned;    // 적립된 크레딧

    @Column(precision = 18, scale = 4, nullable = false)
    private BigDecimal amountPaid;           // 실 결제 금액

    @Column
    private LocalDateTime paidAt;            // 즉시 결제 시 생성 시간

    @Column(length = 120)
    private String receiverName;
    @Column(length = 200)
    private String address;
    @Column(length = 50)
    private String contactPhone;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> items = new ArrayList<>();
}