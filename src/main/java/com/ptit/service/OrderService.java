package com.ptit.service;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.model.Orders;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Orders createOrder(OrderRequestDTO orderRequest);
}