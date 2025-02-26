package com.ptit.controller;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.dto.OrderResponseDTO;
import com.ptit.model.Orders;
import com.ptit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        Orders orders = orderService.createOrder(orderRequest);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrders(userId));
    }
}