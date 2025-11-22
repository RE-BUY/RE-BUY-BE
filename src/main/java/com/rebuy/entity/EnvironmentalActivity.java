package com.rebuy.entity;

import com.rebuy.global.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "environmental_activities")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EnvironmentalActivity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 500)
    private String description;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    private Integer participantLimit;
}