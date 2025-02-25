package com.ptit.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long variantId;
    private int quantity;
}
