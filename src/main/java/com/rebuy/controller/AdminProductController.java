package com.rebuy.controller;

import com.rebuy.dto.product.AdminProductUpdateRequest;
import com.rebuy.entity.Product;
import com.rebuy.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductRepository productRepository;

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody AdminProductUpdateRequest req) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("제품 없음"));

        if (req.getStock() != null) p.setStock(req.getStock());
        if (req.getPrice() != null) p.setPrice(req.getPrice());
        if (req.getEcoBaseScore() != null) p.setEcoBaseScore(req.getEcoBaseScore());
        if (req.getSavedCo2Kg() != null) p.setSavedCo2Kg(req.getSavedCo2Kg());
        if (req.getSavedWaterL() != null) p.setSavedWaterL(req.getSavedWaterL());
        if (req.getSavedOilMl() != null) p.setSavedOilMl(req.getSavedOilMl());
        if (req.getSavedPlasticG() != null) p.setSavedPlasticG(req.getSavedPlasticG());

        productRepository.save(p);
    }

    @PatchMapping("/{id}/stock")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStock(@PathVariable Long id, @RequestBody AdminProductUpdateRequest req) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("제품 없음"));
        if (req.getStock() == null) {
            throw new IllegalArgumentException("stock 필드 필요");
        }
        p.setStock(req.getStock());
        productRepository.save(p);
    }
}