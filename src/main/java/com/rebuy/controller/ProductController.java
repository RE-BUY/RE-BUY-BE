package com.rebuy.controller;

import com.rebuy.controller.dto.product.ProductListResponse;
import com.rebuy.controller.dto.product.ProductResponse;
import com.rebuy.entity.enums.ProductCategory;
import com.rebuy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductListResponse list(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return productService.list(category, q, page, size);
    }

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable Long id) {
        return productService.get(id);
    }
}