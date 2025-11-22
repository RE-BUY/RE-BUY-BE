package com.rebuy.service;

import com.rebuy.dto.order.*;
import com.rebuy.entity.Order;
import com.rebuy.entity.User;
import com.rebuy.entity.enums.OrderStatus;
import com.rebuy.repository.OrderRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public OrderPageResponse list(int page, int size) {
        User user = currentUser();
        var pageable = PageRequest.of(page, size, Sort.by("id").descending());
        var result = orderRepository.findByUser(user, pageable);
        var items = result.getContent().stream()
                .map(o -> OrderSummaryResponse.builder()
                        .orderId(o.getId())
                        .status(o.getStatus())
                        .totalAmount(o.getTotalAmount())
                        .amountPaid(o.getAmountPaid())
                        .creditUsed(o.getTotalCreditUsed())
                        .creditEarned(o.getTotalCreditEarned())
                        .paidAt(o.getPaidAt())
                        .createdAt(o.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
        return OrderPageResponse.builder()
                .items(items)
                .total(result.getTotalElements())
                .page(page)
                .size(size)
                .build();
    }

    @Transactional(readOnly = true)
    public OrderDetailResponse detail(Long id) {
        User user = currentUser();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 주문에 접근 권한이 없습니다.");
        }
        var itemResponses = order.getItems().stream()
                .map(i -> OrderDetailItemResponse.builder()
                        .productId(i.getProduct().getId())
                        .productName(i.getProduct().getName())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .lineAmount(i.getLineAmount())
                        .ecoScore(i.getProduct().getEcoScore())
                        .build())
                .toList();

        return OrderDetailResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .amountPaid(order.getAmountPaid())
                .creditUsed(order.getTotalCreditUsed())
                .creditEarned(order.getTotalCreditEarned())
                .environmentScoreGain(order.getItems().stream()
                        .map(i -> i.getProduct().getEcoScore()
                                .multiply(java.math.BigDecimal.valueOf(i.getQuantity())))
                        .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add))
                .paidAt(order.getPaidAt())
                .createdAt(order.getCreatedAt())
                .receiverName(order.getReceiverName())
                .address(order.getAddress())
                .contactPhone(order.getContactPhone())
                .items(itemResponses)
                .build();
    }

    @Transactional
    public OrderSummaryResponse updateStatus(Long id, String status) {
        User user = currentUser();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("해당 주문에 접근 권한이 없습니다.");
        }
        OrderStatus newStatus = OrderStatus.valueOf(status);
        order.setStatus(newStatus);
        if (newStatus == OrderStatus.PAID && order.getPaidAt() == null) {
            order.setPaidAt(java.time.LocalDateTime.now());
        }
        return OrderSummaryResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .amountPaid(order.getAmountPaid())
                .creditUsed(order.getTotalCreditUsed())
                .creditEarned(order.getTotalCreditEarned())
                .paidAt(order.getPaidAt())
                .createdAt(order.getCreatedAt())
                .build();
    }
}