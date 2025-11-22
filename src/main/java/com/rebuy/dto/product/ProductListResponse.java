package com.rebuy.dto.product;

import com.rebuy.entity.Product;
import com.rebuy.entity.ProductImage;
import com.rebuy.entity.enums.ImageType;

public record ProductListResponse(
        Long id,
        String brand,
        String name,
        int price,
        String thumbnailImage
) {
    public static ProductListResponse from(Product product) {
        String thumbnail = product.getImages().stream()
                .filter(img -> img.getType() == ImageType.THUMBNAIL)
                .map(ProductImage::getImageUrl)
                .findFirst().orElse(null);

        return new ProductListResponse(
                product.getId(),
                product.getBrand(),
                product.getName(),
                product.getPrice(),
                thumbnail
        );
    }
}