package com.rebuy.repository;

import com.rebuy.controller.dto.mypage.EcoStatDto;
import com.rebuy.entity.Order;
import com.rebuy.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    Page<Order> findByUser(User user, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId ORDER BY o.createdAt DESC")
    Page<Order> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId, Pageable pageable);

    @Query("""
        SELECT new com.rebuy.controller.dto.mypage.EcoStatDto(
            FUNCTION('to_char', o.createdAt, 'YYYY-MM'),
            SUM(oi.lineAmount),
            SUM(p.ecoScore * oi.quantity)
        )
        FROM Order o
        JOIN o.items oi
        JOIN oi.product p
        WHERE o.user.id = :userId
          AND o.status <> com.rebuy.entity.enums.OrderStatus.CANCELLED
        GROUP BY FUNCTION('to_char', o.createdAt, 'YYYY-MM')
        ORDER BY FUNCTION('to_char', o.createdAt, 'YYYY-MM') ASC
        """)
    List<EcoStatDto> findMonthlyEcoStats(@Param("userId") Long userId);
}