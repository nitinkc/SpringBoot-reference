package com.spring.reference.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Password must contain at least two numbers and one special character (excluding '@')";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
