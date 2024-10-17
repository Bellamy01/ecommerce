package com.bella.ecommerce.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SummaryDTO {
    private String customerId;
    private double totalCost;
    private double totalShippingCost;
    private double grandTotal;
    private List<OrderItemDTO> items;
}
