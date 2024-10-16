package com.bella.ecommerce.controllers;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.models.Order;
import com.bella.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, String> request) {
        String customerId = request.get("customerId");
        Order order = orderService.createOrder(customerId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Order> addItemToOrder(@PathVariable Long orderId, @RequestBody CreateOrderItemDTO item) {
        Order updatedOrder = orderService.addItemToOrder(orderId, item);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderSummary(@PathVariable Long orderId) {
        Map<String, Object> response = orderService.getOrderSummary(orderId);
        return ResponseEntity.ok(response);
    }
}
