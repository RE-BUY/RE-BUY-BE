package com.rebuy.controller;

import com.rebuy.dto.cart.CartItemRequest;
import com.rebuy.dto.cart.CartResponse;
import com.rebuy.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse addItem(@Valid @RequestBody CartItemRequest request) {
        return cartService.addItem(request);
    }

    @PatchMapping("/items/{productId}")
    public CartResponse updateItem(@PathVariable Long productId,
                                   @Valid @RequestBody CartItemRequest request) {
        return cartService.updateItem(productId, request);
    }

    @DeleteMapping("/items/{productId}")
    public CartResponse removeItem(@PathVariable Long productId) {
        return cartService.removeItem(productId);
    }

    @DeleteMapping("/clear")
    public CartResponse clear() {
        return cartService.clear();
    }
}