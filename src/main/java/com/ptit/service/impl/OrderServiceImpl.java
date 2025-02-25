package com.ptit.service.impl;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.dto.PlaceOrderRequest;
import com.ptit.model.OrderItems;
import com.ptit.model.Orders;
import com.ptit.model.ProductVariants;
import com.ptit.repo.OrderItemRepository;
import com.ptit.repo.OrdersRepository;
import com.ptit.repo.ProductVariantsRepository;
import com.ptit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductVariantsRepository variantRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Orders createOrder(OrderRequestDTO orderRequest) {
        Orders orders = new Orders();
        orders.setCustomerName(orderRequest.getCustomerName());
        orders.setCustomerEmail(orderRequest.getCustomerEmail());
        orders.setTotalPrice(BigDecimal.ZERO);
        orders = ordersRepository.save(orders);
        Integer orderId = orders.getOrderId();

        List<OrderItems> orderItems = orderRequest.getItems().stream().map(itemDTO -> {
            ProductVariants variant = variantRepository.findById(itemDTO.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy biến thể sản phẩm"));

            if (variant.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + variant.getColor() + " không đủ hàng!");
            }

            // Giảm số lượng tồn kho
            variant.setStock(variant.getStock() - itemDTO.getQuantity());
            variantRepository.save(variant);

            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId(orderId);
            orderItem.setVariantId(variant.getVariantId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(variant.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
            return orderItem;
        }).toList();

        // Tính tổng tiền đơn hàng
        BigDecimal totalPrice = orderItems.stream()
                .map(OrderItems::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        orders.setTotalPrice(totalPrice);

        // Lưu đơn hàng vào DBs
        Orders savedOrder = ordersRepository.save(orders);
        orderItemRepository.saveAll(orderItems);

        return savedOrder;
    }
}
