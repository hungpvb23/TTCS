package com.ptit.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceOrderResponse {
    private Integer orderId;
    private String message;
}