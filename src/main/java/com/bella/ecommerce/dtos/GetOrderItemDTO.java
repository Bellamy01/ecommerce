package com.bella.ecommerce.dtos;

import com.bella.ecommerce.enums.OrderType;
import lombok.Data;

@Data
public class GetOrderItemDTO {
    private OrderType type;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
    private Double shippingCost;
    private String downloadLink;
}
