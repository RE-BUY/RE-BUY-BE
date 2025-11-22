package com.rebuy.dto.product;

import com.rebuy.entity.Product;
import com.rebuy.entity.ProductImage;
import com.rebuy.entity.Review;
import com.rebuy.entity.enums.ImageType;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductDetailResponse(
        Long id,
        String brand,           // 2. 브랜드
        String category,        // 3. 카테고리 (한글명)
        String name,            // 상품명
        String modelNumber,     // 4. 모델번호
        int price,              // 5. 가격
        BigDecimal ecoScore,    // 환경 점수
        String description,     // 7. 상세 내용

        // 6~7. 이미지 분리 (프론트가 편하게 쓰도록 나눠줌)
        String thumbnailImage,       // 대표 사진
        List<String> materialImages, // 재료 사진 (업사이클링 재료)
        List<String> detailImages,   // 디테일 사진

        // 8. 리뷰 리스트
        List<ReviewResponse> reviews,
        double averageRating         // 평균 별점 (편의상 추가)
) {
    // 엔티티 -> DTO 변환 메서드 (Service 코드를 깔끔하게 만들기 위함)
    public static ProductDetailResponse from(Product product) {

        // 이미지 분류 로직
        String thumbnail = product.getImages().stream()
                .filter(img -> img.getType() == ImageType.THUMBNAIL)
                .map(ProductImage::getImageUrl)
                .findFirst().orElse(null);

        List<String> materials = product.getImages().stream()
                .filter(img -> img.getType() == ImageType.MATERIAL)
                .map(ProductImage::getImageUrl)
                .toList();

        List<String> details = product.getImages().stream()
                .filter(img -> img.getType() == ImageType.DETAIL)
                .map(ProductImage::getImageUrl)
                .toList();

        // 리뷰 변환
        List<ReviewResponse> reviewList = product.getReviews().stream()
                .map(ReviewResponse::from)
                .toList();

        // 평균 별점 계산
        double avg = product.getReviews().stream()
                .mapToInt(Review::getRating)
                .average().orElse(0.0);

        return new ProductDetailResponse(
                product.getId(),
                product.getBrand(),
                product.getCategory().getTitle(), // Enum의 한글명 ("패션")
                product.getName(),
                product.getModelNumber(),
                product.getPrice(),
                product.getEcoScore(),
                product.getDescription(),
                thumbnail,
                materials,
                details,
                reviewList,
                Math.round(avg * 10) / 10.0 // 소수점 한자리 반올림
        );
    }
}