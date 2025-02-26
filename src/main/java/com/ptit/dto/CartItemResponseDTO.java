package com.ptit.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponseDTO {
    private Long cartItemId;
    private Long userId;
    private Long quantity;
    private BigDecimal price;
    private Long variantId;
    private String color;
    private Long productId;
    private String productName;
    private String description;
    private Long categoryId;
}
