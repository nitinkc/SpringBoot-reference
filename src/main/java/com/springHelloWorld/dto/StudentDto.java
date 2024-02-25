package com.springHelloWorld.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentDto {
    private String fullName;
    private String city;
    private String sex;
    private String university;
    //@Email(message = "Incorrect EmailID received from DB")
    private String emailId;
}
