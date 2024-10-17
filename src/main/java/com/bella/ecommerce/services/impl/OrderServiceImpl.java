package com.bella.ecommerce.services.impl;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.dtos.SummaryDTO;
import com.bella.ecommerce.models.*;
import com.bella.ecommerce.repositories.OrderItemRepository;
import com.bella.ecommerce.repositories.OrderRepository;
import com.bella.ecommerce.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public Order createOrder(String customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);
        return orderRepository.save(order);
    }

    @Transactional
    public Order addItemToOrder(Long orderId, CreateOrderItemDTO itemDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        OrderItem item;

        switch (itemDTO.getType().toUpperCase()) {
            case "PHYSICAL":
                PhysicalProduct physical = new PhysicalProduct();
                physical.setShippingWeight(itemDTO.getShippingWeight());
                item = physical;
                break;

            case "DIGITAL":
                item = new DigitalProduct();
                break;

            case "GIFT_CARD":
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

    public SummaryDTO getOrderSummary(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        String customerId = order.getCustomerId();
        double totalCost = order.calculateTotalCost();
        double totalShippingCost = order.calculateTotalShippingCost();
        double grandTotal = totalCost + totalShippingCost;

        SummaryDTO summary = new SummaryDTO();
        summary.setCustomerId(customerId);
        summary.setTotalCost(totalCost);
        summary.setTotalShippingCost(totalShippingCost);
        summary.setGrandTotal(grandTotal);
        summary.setItems(order.getItems());

        return summary;
    }
}
