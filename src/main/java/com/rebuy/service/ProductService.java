package com.rebuy.service;

import com.rebuy.dto.product.ProductListResponse;
import com.rebuy.dto.product.ProductResponse;
import com.rebuy.entity.Product;
import com.rebuy.entity.enums.ProductCategory;
import com.rebuy.repository.ProductRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductListResponse list(ProductCategory category, String q, int page, int size) {
        Specification<Product> spec = (root, query, cb) -> {
            var predicates = new ArrayList<Predicate>();
            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }
            if (q != null && !q.isBlank()) {
                String like = "%" + q.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("name")), like),
                        cb.like(cb.lower(root.get("description")), like)
                ));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Product> result = productRepository.findAll(spec, PageRequest.of(page, size, Sort.by("id").descending()));
        return ProductListResponse.builder()
                .items(result.stream().map(this::toResponse).toList())
                .total(result.getTotalElements())
                .page(page)
                .size(size)
                .build();
    }

    public ProductResponse get(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));
        return toResponse(p);
    }

    private ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .description(p.getDescription())
                .manufacturer(p.getManufacturer())
                .imageUrl(p.getImageUrl())
                .price(p.getPrice())
                .stock(p.getStock())
                .ecoScore(p.getEcoScore())
                .build();
    }
}