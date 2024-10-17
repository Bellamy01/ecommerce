package com.bella.ecommerce.controllers;

import com.bella.ecommerce.dtos.CreateOrderDTO;
import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.dtos.SummaryDTO;
import com.bella.ecommerce.models.Order;
import com.bella.ecommerce.services.impl.OrderServiceImpl;
import com.bella.ecommerce.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ApiResponse<Order> createOrder(@RequestBody @Valid CreateOrderDTO request) {
        String customerId = request.getCustomerId();
        orderServiceImpl.createOrder(customerId);
        return new ApiResponse<>("Order created successfully", HttpStatus.CREATED, true);
    }

    @PostMapping("/{orderId}/items")
    public ApiResponse<Order> addItemToOrder(@PathVariable Long orderId, @RequestBody @Valid CreateOrderItemDTO item) {
        orderServiceImpl.addItemToOrder(orderId, item);
        return new ApiResponse<>("Item added to order successfully", HttpStatus.CREATED, true);
    }

    @GetMapping("/{orderId}")
    public ApiResponse<SummaryDTO> getOrderSummary(@PathVariable Long orderId) {
       SummaryDTO response = orderServiceImpl.getOrderSummary(orderId);
        return new ApiResponse<>(response, "Order summary retrieved successfully", HttpStatus.OK, true);
    }
}
