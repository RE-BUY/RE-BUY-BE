package com.rebuy.repository;

import com.rebuy.entity.Product;
import com.rebuy.entity.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. 특정 카테고리 상품 조회 (최신순 정렬)
    List<Product> findByCategoryOrderByCreatedAtDesc(Category category);

    // 2. 전체 상품 조회 (최신순 정렬)
    List<Product> findAllByOrderByCreatedAtDesc();
}