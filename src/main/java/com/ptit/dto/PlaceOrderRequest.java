package com.ptit.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequest {
    private String userId;
    private List<OrderItem> items;

    @Data
    public static class OrderItem {
        private Integer productId;
        private Integer quantity;
    }
}