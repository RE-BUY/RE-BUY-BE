package com.rebuy.controller;

import com.rebuy.controller.dto.order.CheckoutRequest;
import com.rebuy.controller.dto.order.OrderDetailResponse;
import com.rebuy.service.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order & Checkout", description = "주문 및 결제 관련 API")
@SecurityRequirement(name = "bearerAuth")
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "장바구니 전체 주문하기",
        description = """
            장바구니에 담긴 모든 상품을 한번에 주문합니다.
            
            **주문 프로세스:**
            1. 장바구니의 모든 상품 확인
            2. 보유 크레딧으로 최대한 결제
            3. 나머지 금액 결제
            4. 결제 금액의 5% 크레딧 적립
            5. 환경 점수 적립
            6. 장바구니 비우기
            
            **크레딧 계산:**
            - 사용: 보유 크레딧 범위 내에서 최대 사용
            - 적립: 실제 결제 금액의 5%
            
            **예시:**
            - 총 금액: 100,000원
            - 보유 크레딧: 30,000원
            - 실제 결제: 70,000원
            - 적립: 3,500원 (70,000 × 5%)
            """
    )
    public OrderDetailResponse checkoutCart(
            @Valid @RequestBody CheckoutRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername();
        return checkoutService.checkoutByUsername(request, username);
    }

    @PostMapping("/checkout/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "[Deprecated] 장바구니 전체 주문 (userId 방식)",
        description = "이전 버전과의 호환성을 위해 유지. `/checkout` 사용을 권장합니다."
    )
    @Deprecated
    public OrderDetailResponse checkout(
            @Valid @RequestBody CheckoutRequest request,
            @PathVariable Long userId
    ) {
        return checkoutService.checkout(request, userId);
    }
}

