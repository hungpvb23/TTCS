package com.ptit.service.impl;

import com.ptit.dto.CartItemResponseDTO;
import com.ptit.model.CartItem;
import com.ptit.repo.CartItemRepository;
import com.ptit.repo.impl.CartItemRepositoryImpl;
import com.ptit.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartItemRepositoryImpl cartItemRepositoryImpl;

    @Override
    public CartItem addToCart(Long userId, Long variantId, int quantity) {
        CartItem cartItem = cartItemRepository.findByUserIdAndVariantId(userId, variantId).orElse(null);
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setVariantId(variantId);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));
        if (quantity <= 0) throw new RuntimeException("Số lượng phải lớn hơn 0");
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItemResponseDTO> getCartItems(Long userId) {
        List<CartItemResponseDTO> cartItems = cartItemRepositoryImpl.getCartItems(userId);
        return cartItems;
    }
}
