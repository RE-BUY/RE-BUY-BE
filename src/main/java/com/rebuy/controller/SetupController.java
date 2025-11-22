package com.rebuy.controller;

import com.rebuy.entity.EnvironmentalActivity;
import com.rebuy.entity.Product;
import com.rebuy.entity.ProductImage;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.Category;
import com.rebuy.entity.enums.ImageType;
import com.rebuy.entity.enums.Role;
import com.rebuy.repository.EnvironmentalActivityRepository; // ★ 추가
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rebuy.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class SetupController {

    private final UserRepository userRepository;
    private final EnvironmentalActivityRepository activityRepository; // ★ 추가
    private final PasswordEncoder passwordEncoder;

    // ... 기존 /setup 코드는 생략 (그대로 두세요) ...

    // ★ 이 메서드를 추가하세요!
    @GetMapping("/setup-activity")
    public String setupActivity() {
        try {
            EnvironmentalActivity activity = EnvironmentalActivity.builder()
                    .name("한강 플로깅 모임")
                    .description("이번 주말 한강에서 쓰레기 같이 주워요! 봉투 제공합니다.")
                    .participantLimit(5) // 5명 선착순
                    .startAt(LocalDateTime.now().plusDays(3)) // 3일 뒤 시작
                    .endAt(LocalDateTime.now().plusDays(3).plusHours(2)) // 2시간 동안
                    .build();

            activityRepository.save(activity);

            return "<h1>✅ 활동 데이터 생성 성공!</h1>" +
                    "<p>활동명: " + activity.getName() + "</p>" +
                    "<p>ID: <b>" + activity.getId() + "</b> (이 번호를 기억하세요!)</p>";

        } catch (Exception e) {
            return "<h1>❌ 실패...</h1>" + e.getMessage();
        }
    }
    private final ProductRepository productRepository; // 생성자 주입 추가

    @GetMapping("/setup-products")
    public String setupProducts() {
        try {
            // 상품 1 생성
            Product p1 = Product.builder()
                    .brand("FREITAG")
                    .name("리사이클링 지갑 F554")
                    .category(Category.FASHION)
                    .modelNumber("F554 MAX 0024")
                    .price(169200)
                    .stock(10)
                    .description("트럭 방수포를 재활용한 힙한 지갑입니다.")
                    .build();

            // 이미지 1 (썸네일)
            ProductImage img1 = ProductImage.builder()
                    .product(p1)
                    .type(ImageType.THUMBNAIL)
                    .imageUrl("https://freitag.com/dummy/thumb.jpg") // 가짜 URL
                    .build();

            p1.getImages().add(img1);

            productRepository.save(p1);

            return "✅ 상품 데이터 생성 완료! ID: " + p1.getId();
        } catch (Exception e) {
            return "❌ 실패: " + e.getMessage();
        }
    }
}