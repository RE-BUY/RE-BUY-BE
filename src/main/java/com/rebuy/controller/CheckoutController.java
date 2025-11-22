package com.rebuy.controller;

import com.rebuy.controller.dto.order.CheckoutRequest;
import com.rebuy.controller.dto.order.OrderDetailResponse;
import com.rebuy.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/checkout/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailResponse checkout(@Valid @RequestBody CheckoutRequest request, @PathVariable Long userId) {
        return checkoutService.checkout(request, userId);
    }
}