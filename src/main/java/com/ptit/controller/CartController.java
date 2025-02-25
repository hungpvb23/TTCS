package com.ptit.controller;

import com.ptit.dto.CartItemResponseDTO;
import com.ptit.dto.CartRequestDTO;
import com.ptit.model.CartItem;
import com.ptit.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartService;

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/add")
    public ResponseEntity<CartItem> addProductToCart(@RequestBody CartRequestDTO request) {
        return ResponseEntity.ok(cartService.addToCart(request.getUserId(), request.getVariantId(), request.getQuantity()));
    }

    // 2️⃣ Cập nhật số lượng sản phẩm
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCart(@PathVariable Long cartItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.updateCartItem(cartItemId, quantity));
    }

    // 3️⃣ Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng");
    }

    // 5️⃣ Lấy danh sách giỏ hàng
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }

}
