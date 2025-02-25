package com.ptit.controller;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.model.Orders;
import com.ptit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}