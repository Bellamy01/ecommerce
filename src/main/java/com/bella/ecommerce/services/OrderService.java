package com.bella.ecommerce.services;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.dtos.SummaryDTO;
import com.bella.ecommerce.models.Order;

public interface OrderService {
    Order createOrder(String customerId);
    Order addItemToOrder(Long orderId, CreateOrderItemDTO itemDTO);
    SummaryDTO getOrderSummary(Long orderId);
}
