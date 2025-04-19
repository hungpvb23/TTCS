package com.ptit.dto;

import lombok.Data;

@Data
public class ProductSearchOutput {
    private Long productId;
    private Long variantId;
    private String productName;
    private String description;
    private String price;
    private Integer stock;
    private String imageUrl;
    private String categoryName;
    private String color;
}