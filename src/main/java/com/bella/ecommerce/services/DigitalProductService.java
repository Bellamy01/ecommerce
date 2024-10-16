package com.bella.ecommerce.services;

import com.bella.ecommerce.models.DigitalProduct;
import org.springframework.stereotype.Service;

@Service
public class DigitalProductService {
    public String generateDownloadLink(DigitalProduct product) {
        return product.generateDownloadLink();
    }
}
