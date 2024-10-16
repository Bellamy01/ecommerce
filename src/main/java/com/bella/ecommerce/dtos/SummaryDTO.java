package com.bella.ecommerce.dtos;

import com.bella.ecommerce.models.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class SummaryDTO {
    private String customerId;
    private double totalCost;
    private double totalShippingCost;
    private double grandTotal;
    private List<OrderItem> items;

}
