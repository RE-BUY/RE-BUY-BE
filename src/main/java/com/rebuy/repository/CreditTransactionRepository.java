package com.rebuy.repository;

import com.rebuy.entity.CreditTransaction;
import com.rebuy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, Long> {
    List<CreditTransaction> findByUserOrderByOccurredAtDesc(User user);
}