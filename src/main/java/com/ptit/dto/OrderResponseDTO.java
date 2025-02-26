package com.ptit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private Long orderItemId;
    private Long quantity;
}
