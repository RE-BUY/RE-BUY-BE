package com.rebuy.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    FOOD("식품"),
    BEVERAGE("음료"),
    FASHION("패션"),
    PROPS("소품"),       // 소품
    STATIONERY("문구류"),
    LIVING("생활용품");

    private final String title;
}