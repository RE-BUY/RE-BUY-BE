package com.rebuy.global.exception.config;

import com.rebuy.entity.Product;
import com.rebuy.entity.enums.ProductCategory;
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

    @Bean
    CommandLineRunner seedProducts() {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(Product.builder()
                        .name("Reusable Eco Bottle")
                        .category(ProductCategory.HOUSEHOLD)
                        .description("친환경 스테인리스 텀블러")
                        .manufacturer("EcoMakers")
                        .imageUrl("https://example.com/img/bottle.jpg")
                        .price(new BigDecimal("18000"))
                        .stock(120)
                        .ecoScore(new BigDecimal("12.5"))
                        .build());
                productRepository.save(Product.builder()
                        .name("Organic Snack Pack")
                        .category(ProductCategory.FOOD)
                        .description("무첨가 친환경 포장 간식 세트")
                        .manufacturer("GreenFarm")
                        .imageUrl("https://example.com/img/snack.jpg")
                        .price(new BigDecimal("8500"))
                        .stock(300)
                        .ecoScore(new BigDecimal("5.2"))
                        .build());
                productRepository.save(Product.builder()
                        .name("Solar Powered Charger")
                        .category(ProductCategory.ELECTRONICS)
                        .description("태양광 휴대용 충전기")
                        .manufacturer("SunCharge")
                        .imageUrl("https://example.com/img/solar.jpg")
                        .price(new BigDecimal("42000"))
                        .stock(50)
                        .ecoScore(new BigDecimal("25.0"))
                        .build());
            }
        };
    }
}