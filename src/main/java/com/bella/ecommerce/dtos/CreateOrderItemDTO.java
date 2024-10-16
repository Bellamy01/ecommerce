package com.bella.ecommerce.dtos;

import com.bella.ecommerce.enums.OrderType;
import lombok.Data;

@Data
public class CreateOrderItemDTO {
    private OrderType type;
    private String productName;
    private double price;
    private int quantity;
    private Double shippingWeight;
    private String recipientEmail;
}
