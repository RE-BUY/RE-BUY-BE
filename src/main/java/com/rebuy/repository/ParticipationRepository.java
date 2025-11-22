package com.rebuy.repository;

import com.rebuy.entity.Participation;
import com.rebuy.entity.User;
import com.rebuy.entity.EnvironmentalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    // 1. 중복 신청 방지: 이미 신청했는지 확인
    boolean existsByUserAndActivity(User user, EnvironmentalActivity activity);

    // 2. 인원 제한 체크: 현재 몇 명이 신청했는지 카운트
    int countByActivity(EnvironmentalActivity activity);
}