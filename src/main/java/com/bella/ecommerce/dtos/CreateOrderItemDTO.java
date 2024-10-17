package com.bella.ecommerce.dtos;

import com.bella.ecommerce.validators.ValidEmail;
import com.bella.ecommerce.validators.ValidType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class CreateOrderItemDTO {
    @NotNull(message = "Type is required")
    @ValidType(message = "Please provide a valid type")
    private String type;
    @NotNull(message = "Product name is required")
    @Size(min = 2, max = 50, message = "Product name must be 2-50 characters long")
    private String productName;
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private double price;
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
    @Min(value = 0, message = "Shipping weight must be greater than or equal to 0")
    private Double shippingWeight;
    @ValidEmail(message = "Please provide a valid email")
    private String recipientEmail;
}
