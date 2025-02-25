package com.ptit.service;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.model.Orders;

public interface OrderService {
    Orders createOrder(OrderRequestDTO orderRequest);
}