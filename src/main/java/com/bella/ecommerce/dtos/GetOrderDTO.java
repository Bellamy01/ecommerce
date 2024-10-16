package com.bella.ecommerce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class GetOrderDTO {
    private Long orderId;
    private List<GetOrderDTO> items;
    private double totalPrice;
    private double totalShippingCost;
    private double grandTotal;
}
