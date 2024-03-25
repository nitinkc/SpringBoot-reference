package com.spring.reference.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExampleResponse {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private ZonedDateTime zonedDateTime;

    @NotNull(message = "Phone number cannot be null")
    private String phone;
}
