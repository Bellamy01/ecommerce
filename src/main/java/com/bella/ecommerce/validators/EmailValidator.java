package com.bella.ecommerce.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

  @Override
  public void initialize(ValidEmail constraintAnnotation) {
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null) {
      return true;
    }
    return email.matches(EMAIL_PATTERN);
  }
}