package com.ptit.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    private String customerName;
    private String customerEmail;
    private List<OrderItemDTO> items;
}