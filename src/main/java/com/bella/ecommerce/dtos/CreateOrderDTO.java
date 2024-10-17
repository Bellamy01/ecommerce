package com.bella.ecommerce.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class CreateOrderDTO {
    @NotBlank(message = "Customer ID is required")
    private String customerId;
}
