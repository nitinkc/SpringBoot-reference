package com.spring.reference.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
public class ExampleResponse {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private ZonedDateTime zonedDateTime;

    @NotNull(message = "Phone number cannot be null")
    private String phone;
}
