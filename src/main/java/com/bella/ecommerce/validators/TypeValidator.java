package com.bella.ecommerce.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class TypeValidator implements ConstraintValidator<ValidType, String> {

  private final Set<String> validTypes = Set.of("DIGITAL", "PHYSICAL", "GIFT_CARD");

  @Override
  public void initialize(ValidType constraintAnnotation) {
  }

  @Override
  public boolean isValid(String type, ConstraintValidatorContext context) {
    if (type == null) {
      return true;
    }
    return validTypes.contains(type.toUpperCase());
  }
}