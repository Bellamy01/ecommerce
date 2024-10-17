package com.bella.ecommerce.dtos;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private String productName;
    private double price;
    private int quantity;
    private String type;
    private double totalPrice;
    private double shippingCost;
    private String downloadLink;
}
