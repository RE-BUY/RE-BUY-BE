package com.rebuy.repository;

import com.rebuy.entity.Order;
import com.rebuy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    Page<Order> findByUser(User user, Pageable pageable);
}