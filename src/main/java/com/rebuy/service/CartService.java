package com.rebuy.service;

import com.rebuy.dto.cart.CartItemRequest;
import com.rebuy.dto.cart.CartItemResponse;
import com.rebuy.dto.cart.CartResponse;
import com.rebuy.entity.CartItem;
import com.rebuy.entity.Product;
import com.rebuy.entity.User;
import com.rebuy.repository.CartItemRepository;
import com.rebuy.repository.ProductRepository;
import com.rebuy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public CartResponse addItem(CartItemRequest request) {
        User user = currentUser();
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));

        int qty = request.getQuantity() == null ? 1 : request.getQuantity();
        cartItemRepository.findByUserAndProduct(user, product).ifPresentOrElse(
                existing -> existing.setQuantity(existing.getQuantity() + qty),
                () -> cartItemRepository.save(CartItem.builder()
                        .user(user)
                        .product(product)
                        .quantity(qty)
                        .build())
        );
        return buildCart(user);
    }

    @Transactional
    public CartResponse updateItem(Long productId, CartItemRequest request) {
        User user = currentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));
        CartItem item = cartItemRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 없습니다."));
        int qty = request.getQuantity() == null ? 1 : request.getQuantity();
        item.setQuantity(qty);
        return buildCart(user);
    }

    @Transactional
    public CartResponse removeItem(Long productId) {
        User user = currentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));
        cartItemRepository.findByUserAndProduct(user, product)
                .ifPresent(cartItemRepository::delete);
        return buildCart(user);
    }

    @Transactional
    public CartResponse clear() {
        User user = currentUser();
        cartItemRepository.deleteByUser(user);
        return buildCart(user);
    }

    @Transactional(readOnly = true)
    public CartResponse getCart() {
        return buildCart(currentUser());
    }

    private CartResponse buildCart(User user) {
        List<CartItem> items = cartItemRepository.findByUser(user);

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal ecoTotal = BigDecimal.ZERO;
        List<CartItemResponse> itemResponses = new ArrayList<>();

        for (CartItem ci : items) {
            Product p = ci.getProduct();
            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()));
            total = total.add(line);
            ecoTotal = ecoTotal.add(p.getEcoScore().multiply(BigDecimal.valueOf(ci.getQuantity())));

            itemResponses.add(
                    CartItemResponse.builder()
                            .productId(p.getId())
                            .productName(p.getName())
                            .quantity(ci.getQuantity())
                            .unitPrice(p.getPrice())
                            .lineAmount(line)
                            .ecoScore(p.getEcoScore())
                            .build()
            );
        }

        return CartResponse.builder()
                .items(itemResponses)
                .totalAmount(total)
                .totalEcoScore(ecoTotal)
                .build();
    }
}