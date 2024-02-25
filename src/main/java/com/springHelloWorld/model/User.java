package com.springHelloWorld.model;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	@Size(min=2,message = "Names should be at-least 2 characters long")
	private String name;
	@Past(message = "DOB Cannot be in the Future")
	private Date dob;
}
