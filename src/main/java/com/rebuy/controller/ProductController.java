package com.rebuy.controller;

import com.rebuy.dto.product.ProductDetailResponse;
import com.rebuy.dto.product.ProductListResponse;
import com.rebuy.entity.enums.Category;
import com.rebuy.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 관련 API")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "상품 목록 조회", description = "category 파라미터가 없으면 전체, 있으면 해당 카테고리 반환")
    public ResponseEntity<List<ProductListResponse>> getProducts(
            @RequestParam(required = false) Category category
    ) {
        return ResponseEntity.ok(productService.getProducts(category));
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 상세 조회", description = "상품 ID로 상세 정보를 조회합니다.")
    public ResponseEntity<ProductDetailResponse> getProductDetail(
            @PathVariable Long productId
    ) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }
}