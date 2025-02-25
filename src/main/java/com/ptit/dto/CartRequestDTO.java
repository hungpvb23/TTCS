package com.ptit.dto;

import lombok.Data;

@Data
public class CartRequestDTO {
    private Long userId;
    private Long variantId;
    private int quantity;
}
