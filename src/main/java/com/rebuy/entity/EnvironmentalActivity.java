package com.rebuy.entity;

import com.rebuy.entity.base.BaseTimeEntity;
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

    // 활동명
    @Column(nullable = false, length = 120)
    private String name;

    // 설명
    @Column(length = 500)
    private String description;

    // 시작/종료 시간
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    // 참여 인원 제한 (null => 제한 없음)
    private Integer participantLimit;
}