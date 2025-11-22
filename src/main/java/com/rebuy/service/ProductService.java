package com.rebuy.service;

import com.rebuy.dto.product.ProductDetailResponse;
import com.rebuy.dto.product.ProductListResponse;
import com.rebuy.entity.Product;
import com.rebuy.entity.enums.Category;
import com.rebuy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 1. 상품 목록 조회 (메인 페이지)
     */
    public List<ProductListResponse> getProducts(Category category) {
        List<Product> products;

        if (category == null) {
            // 전체 조회
            products = productRepository.findAllByOrderByCreatedAtDesc();
        } else {
            // 카테고리별 조회
            products = productRepository.findByCategoryOrderByCreatedAtDesc(category);
        }

        // DTO 변환 (ProductListResponse.from 메서드 활용)
        return products.stream()
                .map(ProductListResponse::from)
                .collect(Collectors.toList());
    }

    /**
     * 2. 상품 상세 조회 (상세 페이지)
     */
    public ProductDetailResponse getProductDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productId));

        // DTO 변환 (ProductDetailResponse.from 메서드 활용)
        return ProductDetailResponse.from(product);
    }
}