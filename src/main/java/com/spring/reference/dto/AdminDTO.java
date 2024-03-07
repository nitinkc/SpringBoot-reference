package com.spring.reference.dto;

import com.spring.reference.exception.PasswordConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminDTO {

    @NotBlank(message = "Username cannot be blank", groups = AdminValidation.class)
    private String username;

    @NotBlank(message = "Password cannot be blank", groups = AdminValidation.class)
    @Size(min = 8, message = "Password must be at least 8 characters long", groups = AdminValidation.class)
    @PasswordConstraint(message = "Password must contain at least two numbers and one special character (excluding '@')", groups = AdminValidation.class)
    private String password;

    // Other fields, constructors, getters, and setters

    // Define validation group for admin DTO
    public interface AdminValidation {}
}
