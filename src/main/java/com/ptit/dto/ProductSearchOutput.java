package com.ptit.dto;

import lombok.Data;

@Data
public class ProductSearchOutput {
    private String productName;
    private String description;
    private double price;
    private int stock;
    private String imageUrl;
    private String categoryName;
    private int parentId;
}
