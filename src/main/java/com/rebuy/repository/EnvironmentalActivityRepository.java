package com.rebuy.repository;

import com.rebuy.entity.EnvironmentalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironmentalActivityRepository extends JpaRepository<EnvironmentalActivity, Long> {
    // 추후 지역별 조회 등이 필요하면 여기에 추가
    // List<EnvironmentalActivity> findByRegion(String region);
}