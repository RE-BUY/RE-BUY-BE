package com.rebuy.service;

import com.rebuy.controller.dto.dashboard.DashboardResponse;
import com.rebuy.controller.dto.order.OrderResponse;
import com.rebuy.entity.Order;
import com.rebuy.entity.OrderItem;
import com.rebuy.entity.User;
import com.rebuy.repository.OrderRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public DashboardResponse getDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));

        List<Order> orders = orderRepository.findByUser(user);
        List<OrderResponse> recent = orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .map(this::map)
                .toList();

        long pineTrees = user.getEnvironmentScore()
                .divide(BigDecimal.valueOf(10), 0, java.math.RoundingMode.DOWN)
                .longValue();

        return DashboardResponse.builder()
                .username(user.getUsername())
                .creditBalance(user.getCreditBalance())
                .environmentScore(user.getEnvironmentScore())
                .pineTreeEquivalent(pineTrees)
                .recentOrders(recent)
                .build();
    }

    private OrderResponse map(Order o) {
        OrderItem item = o.getItems().isEmpty() ? null : o.getItems().get(0);
        BigDecimal totalAmount = o.getTotalAmount();
        BigDecimal creditUsed = o.getTotalCreditUsed();
        BigDecimal amountPaid = totalAmount.subtract(creditUsed);
        BigDecimal creditEarned = amountPaid.multiply(BigDecimal.valueOf(0.05));

        return OrderResponse.builder()
                .orderId(o.getId())
                .productId(item != null ? item.getProduct().getId() : null)
                .productName(item != null ? item.getProduct().getName() : "(no item)")
                .quantity(item != null ? item.getQuantity() : 0)
                .totalProductAmount(totalAmount)
                .creditUsed(creditUsed)
                .creditEarned(creditEarned)
                .amountPaid(amountPaid)
                .orderedAt(o.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant())
                .build();
    }
}