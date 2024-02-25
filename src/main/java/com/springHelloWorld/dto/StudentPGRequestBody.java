package com.springHelloWorld.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StudentPGRequestBody {
    private String firstName;
    private String lastName;
    private String gender;
    private String cityofbirth;
    private String email;
    private String university;
    private Date dob;
}
