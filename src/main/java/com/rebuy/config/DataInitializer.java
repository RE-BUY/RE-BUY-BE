package com.rebuy.config;

import com.rebuy.entity.Product;
import com.rebuy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final ProductRepository productRepository;

    // @Bean
    CommandLineRunner initData() {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(Product.builder()
                        .name("Eco Bottle")
                        .description("Reusable stainless steel bottle")
                        .price(new BigDecimal("18000"))
                        .stock(150)
                        .ecoScore(new BigDecimal("12.5"))
                        .build());
            }
        };
    }
}