package com.ptit.service;

import com.ptit.dto.CartItemResponseDTO;
import com.ptit.model.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(Long userId, Long variantId, int quantity);

    CartItem updateCartItem(Long cartItemId, int quantity);

    void removeCartItem(Long cartItemId);

    List<CartItemResponseDTO> getCartItems(Long userId);
}
