package com.bella.ecommerce.services;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.enums.OrderType;
import com.bella.ecommerce.models.*;
import com.bella.ecommerce.repositories.OrderItemRepository;
import com.bella.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order createOrder(String customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);
        return orderRepository.save(order);
    }

    public Order addItemToOrder(Long orderId, CreateOrderItemDTO itemDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem item;

        switch (itemDTO.getType()) {
            case OrderType.physical:
                PhysicalProduct physical = new PhysicalProduct();
                physical.setShippingWeight(itemDTO.getShippingWeight());
                item = physical;
                break;

            case OrderType.digital:
                item = new DigitalProduct();
                break;

            case OrderType.gift_card:
                GiftCard giftCard = new GiftCard();
                giftCard.setRecipientEmail(itemDTO.getRecipientEmail());
                item = giftCard;
                break;

            default:
                throw new IllegalArgumentException("Invalid item type: " + itemDTO.getType());
        }

        item.setProductName(itemDTO.getProductName());
        item.setPrice(itemDTO.getPrice());
        item.setQuantity(itemDTO.getQuantity());

        orderItemRepository.save(item);
        order.addItem(item);
        return orderRepository.save(order);
    }

    public Map<String, Object> getOrderSummary(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String customerId = order.getCustomerId();
        double totalCost = order.calculateTotalCost();
        double totalShippingCost = order.calculateTotalShippingCost();
        double grandTotal = totalCost + totalShippingCost;

        return Map.of(
                    "customerId", customerId,
                "totalCost", totalCost,
                "totalShippingCost", totalShippingCost,
                "grandTotal", grandTotal,
                "items", order.getItems()
        );
    }
}
