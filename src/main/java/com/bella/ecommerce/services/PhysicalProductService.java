package com.bella.ecommerce.services;

import com.bella.ecommerce.models.PhysicalProduct;
import org.springframework.stereotype.Service;

@Service
public class PhysicalProductService {
    public double calculateShippingCost(PhysicalProduct product) {
        return product.calculateShippingCost();
    }
}
