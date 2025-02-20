package com.ptit.service;

import com.ptit.dto.PlaceOrderRequest;
import com.ptit.dto.PlaceOrderResponse;

public interface OrderService {
    PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);
}