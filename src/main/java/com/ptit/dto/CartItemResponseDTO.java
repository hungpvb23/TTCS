package com.ptit.dto;

import lombok.Data;

@Data
public class CartItemResponseDTO {
    private Long cartItemId;
    private Long variantId;
    private int quantity;
}
