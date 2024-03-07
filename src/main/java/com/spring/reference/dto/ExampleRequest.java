package com.spring.reference.dto;

import com.spring.reference.exception.ValidPhoneNumber;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ExampleRequest {

  @NotBlank(message = "Name cannot be blank")
  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only alphabetical characters")
  private String name;

  @NotBlank
  @ValidPhoneNumber(message = "Can be in the format {1111111111, (111) 111 1111, 111-111-1111}")
  private String phoneNumber;
}
