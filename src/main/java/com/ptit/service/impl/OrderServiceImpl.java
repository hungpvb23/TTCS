package com.ptit.service.impl;

import com.ptit.dto.OrderRequestDTO;
import com.ptit.dto.OrderResponseDTO;
import com.ptit.model.CartItem;
import com.ptit.model.OrderItems;
import com.ptit.model.Orders;
import com.ptit.model.ProductVariants;
import com.ptit.repo.*;
import com.ptit.repo.impl.OrdersRepositoryImpl;
import com.ptit.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ProductVariantsRepository variantRepository;

    private final OrdersRepository ordersRepository;

    private final OrdersRepositoryImpl ordersRepositoryImpl;

    private final OrderItemRepository orderItemRepository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    @Override
    public Orders createOrder(OrderRequestDTO orderRequest) {
        Long userId = orderRequest.getUserId();

        List<Long> selectedCartItemIds = orderRequest.getCartItemIds();
        if (selectedCartItemIds == null || selectedCartItemIds.isEmpty()) {
            throw new RuntimeException("Bạn phải chọn ít nhất một sản phẩm để đặt hàng!");
        }

        // Lấy danh sách sản phẩm đã chọn từ giỏ hàng
        List<CartItem> selectedCartItems = cartItemRepository.findSelectedCartItems(userId, selectedCartItemIds);
        if (selectedCartItems.isEmpty()) {
            throw new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng!");
        }

        // Tạo danh sách order_items từ cart_items
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setTotalPrice(BigDecimal.ZERO);
        orders = ordersRepository.save(orders);
        Integer orderId = orders.getOrderId();

        // Tạo danh sách order_items từ cart_items
        List<OrderItems> orderItems = selectedCartItems.stream().map(cartItem -> {
            ProductVariants variant = variantRepository.findById(cartItem.getVariantId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy biến thể sản phẩm"));

            if (variant.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + variant.getColor() + " không đủ hàng!");
            }

            // Giảm số lượng tồn kho
            variant.setStock(variant.getStock() - cartItem.getQuantity());
            variantRepository.save(variant);

            OrderItems orderItem = new OrderItems();
            orderItem.setOrderId(orderId);
            orderItem.setVariantId(variant.getVariantId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(variant.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            return orderItem;
        }).toList();

        // Tính tổng tiền đơn hàng
        BigDecimal totalPrice = orderItems.stream()
                .map(OrderItems::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        orders.setTotalPrice(totalPrice);

        // Lưu đơn hàng vào DB
        Orders savedOrder = ordersRepository.save(orders);
        orderItemRepository.saveAll(orderItems);

        // Xóa sản phẩm đã đặt hàng khỏi giỏ hàng
        cartItemRepository.deleteByIdIn(selectedCartItems.stream().map(CartItem::getId).toList());

        return savedOrder;
    }

    @Override
    public List<OrderResponseDTO> getOrders(Long userId) {
        return ordersRepositoryImpl.findAllOrdersByUserId(userId);
    }
}
