package com.bella.ecommerce.services.impl;

import com.bella.ecommerce.dtos.CreateOrderItemDTO;
import com.bella.ecommerce.dtos.OrderItemDTO;
import com.bella.ecommerce.dtos.SummaryDTO;
import com.bella.ecommerce.models.*;
import com.bella.ecommerce.repositories.OrderItemRepository;
import com.bella.ecommerce.repositories.OrderRepository;
import com.bella.ecommerce.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<OrderItemDTO> items = new java.util.ArrayList<>(new java.util.ArrayList<>(order.getItems().stream()
                .map(item -> {
                    OrderItemDTO itemDTO = new OrderItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductName(item.getProductName());
                    itemDTO.setPrice(item.getPrice());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setType(item instanceof PhysicalProduct ? "PHYSICAL" : item instanceof DigitalProduct ? "DIGITAL" : "GIFT_CARD");
                    itemDTO.setTotalPrice(item.getTotalPrice());
                    itemDTO.setShippingCost(item instanceof PhysicalProduct ? ((PhysicalProduct) item).calculateShippingCost() : 0);
                    itemDTO.setDownloadLink(item instanceof DigitalProduct ? ((DigitalProduct) item).generateDownloadLink() : null);
                    return itemDTO;
                })
                .toList()));

        SummaryDTO summary = new SummaryDTO();
        summary.setCustomerId(customerId);
        summary.setTotalCost(totalCost);
        summary.setTotalShippingCost(totalShippingCost);
        summary.setGrandTotal(grandTotal);
        summary.setItems(items);

        return summary;
    }
}
