package com.bella.ecommerce.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
  String message() default "Invalid email address";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}