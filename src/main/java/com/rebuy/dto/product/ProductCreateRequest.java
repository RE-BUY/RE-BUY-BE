package com.rebuy.dto.product;

import com.rebuy.entity.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
        @NotBlank(message = "브랜드는 필수입니다.")
        String brand,

        @NotBlank(message = "상품명은 필수입니다.")
        String name,

        @NotNull(message = "카테고리는 필수입니다.")
        Category category, // "FASHION", "FOOD" 등으로 들어옴

        String modelNumber,

        @Min(value = 100, message = "가격은 100원 이상이어야 합니다.")
        int price,

        @Min(value = 1, message = "재고는 1개 이상이어야 합니다.")
        int stock,

        String description
) {}