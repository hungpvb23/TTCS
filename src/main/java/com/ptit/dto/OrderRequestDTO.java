package com.ptit.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Long userId;
    private String customer;
    private String address;
    private List<CartRequestDTO> productList;
}