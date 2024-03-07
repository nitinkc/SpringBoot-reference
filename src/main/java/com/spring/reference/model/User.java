package com.spring.reference.model;

import com.spring.reference.exception.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Data
@RequiredArgsConstructor
@Table(name = "users")
public class User {
	@Id
	private UUID id;

	@NotBlank(message = "Name is required")
	@Size(min=3,message = "Names should be at-least 3 characters long")
	private String name;

	@Past(message = "DOB Cannot be in the Future")
	@Column(name = "date_of_birth")
	private Date dob;

	@ValidPhoneNumber(message = "Can be in the format {1111111111, (111) 111 1111, 111-111-1111}")
	private String phone;

	@Email(message = "Please provide a valid email address")
	private String email;
}