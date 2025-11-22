package com.rebuy.controller;

import com.rebuy.dto.order.OrderDetailResponse;
import com.rebuy.dto.order.OrderPageResponse;
import com.rebuy.dto.order.OrderStatusUpdateRequest;
import com.rebuy.dto.order.OrderSummaryResponse;
import com.rebuy.service.OrderQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    @GetMapping
    public OrderPageResponse list(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        return orderQueryService.list(page, size);
    }

    @GetMapping("/{id}")
    public OrderDetailResponse detail(@PathVariable Long id) {
        return orderQueryService.detail(id);
    }

    @PatchMapping("/{id}/status")
    public OrderSummaryResponse updateStatus(@PathVariable Long id,
                                             @Valid @RequestBody OrderStatusUpdateRequest request) {
        return orderQueryService.updateStatus(id, request.getStatus());
    }
}