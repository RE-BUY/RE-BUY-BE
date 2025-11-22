package com.rebuy.repository;

import com.rebuy.entity.EnvironmentalImpact;
import com.rebuy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnvironmentalImpactRepository extends JpaRepository<EnvironmentalImpact, Long> {
    Optional<EnvironmentalImpact> findByProduct(Product product);
}