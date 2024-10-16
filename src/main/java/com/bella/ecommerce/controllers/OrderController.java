package com.bella.ecommerce.controllers;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.dtos.SummaryDTO;
import com.bella.ecommerce.models.Order;
import com.bella.ecommerce.services.impl.OrderServiceImpl;
import com.bella.ecommerce.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }

    @PostMapping
    public ApiResponse<Order> createOrder(@RequestBody Map<String, String> request) {
        String customerId = request.get("customerId");
        Order order = orderServiceImpl.createOrder(customerId);
        return new ApiResponse<>(order, "Order created successfully", null, true);
    }

    @PostMapping("/{orderId}/items")
    public ApiResponse<Order> addItemToOrder(@PathVariable Long orderId, @RequestBody CreateOrderItemDTO item) {
        Order updatedOrder = orderServiceImpl.addItemToOrder(orderId, item);
        return new ApiResponse<>(updatedOrder, "Item added to order successfully", null, true);
    }

    @GetMapping("/{orderId}")
    public ApiResponse<SummaryDTO> getOrderSummary(@PathVariable Long orderId) {
       SummaryDTO response = orderServiceImpl.getOrderSummary(orderId);
        return new ApiResponse<>(response, "Order summary retrieved successfully", null, true);
    }
}
