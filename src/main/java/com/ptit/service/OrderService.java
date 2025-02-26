package com.ptit.service;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.dto.OrderResponseDTO;
import com.ptit.model.Orders;

import java.util.List;

public interface OrderService {
    Orders createOrder(OrderRequestDTO orderRequest);

    List<OrderResponseDTO> getOrders(Long userId);
}