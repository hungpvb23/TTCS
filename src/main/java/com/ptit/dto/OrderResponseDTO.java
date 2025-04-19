package com.ptit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private BigDecimal varitantPrice;
    private LocalDateTime createdAt;
    private Long orderItemId;
    private Long quantity;
    private String customer;
    private String address;
    private String color;
    private String productName;
}
