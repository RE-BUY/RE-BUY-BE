package com.rebuy.entity;

import com.rebuy.entity.enums.ImageType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_images")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 이미지 URL (S3 등에서 받은 주소)
    @Column(nullable = false, length = 500)
    private String imageUrl;

    // 이미지 타입 (썸네일/상세/재료)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType type;

    // 재료 이름 (타입이 MATERIAL일 때만 사용, 예: "안전벨트")
    private String materialName;
}