package com.bella.ecommerce.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = TypeValidator.class)
public @interface ValidType {
  String message() default "Invalid gender";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}