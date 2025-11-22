package com.rebuy.repository;

import com.rebuy.dto.mypage.EcoStatDto;
import com.rebuy.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 1. 최근 주문 내역 조회 (페이징 처리로 개수 제한)
    // User -> BaseTimeEntity 상속이므로 createdAt 사용
    List<Order> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // 2. [핵심] 월별 환경 점수 통계 쿼리
    // Order -> OrderItem -> Product(ecoScore)를 조인하여 계산
    // PostgreSQL의 to_char 함수를 사용하여 'YYYY-MM' 형태로 그룹핑
    @Query("""
        SELECT new com.rebuy.dto.mypage.EcoStatDto(
            FUNCTION('to_char', o.createdAt, 'YYYY-MM'),
            SUM(p.ecoScore * oi.quantity)
        )
        FROM Order o
        JOIN o.items oi
        JOIN oi.product p
        WHERE o.user.id = :userId
        AND o.status != 'CANCELLED' 
        GROUP BY FUNCTION('to_char', o.createdAt, 'YYYY-MM')
        ORDER BY FUNCTION('to_char', o.createdAt, 'YYYY-MM') ASC
    """)
    List<EcoStatDto> findMonthlyEcoStats(@Param("userId") Long userId);
}