package com.springHelloWorld.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name="student")
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String cityofbirth;
    private String email;
    private String university;
    private Date dob;
}
