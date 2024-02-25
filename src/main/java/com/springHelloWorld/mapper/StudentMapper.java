package com.springHelloWorld.mapper;

import com.springHelloWorld.dto.StudentDto;
import com.springHelloWorld.dto.StudentPgDto;
import com.springHelloWorld.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto convert(Student studentById) {
        return StudentDto.builder()
                .fullName(studentById.getFirstName() + " " + studentById.getLastName())
                .city(studentById.getCityofbirth())
                .sex(studentById.getGender() == null ? "":studentById.getGender())
                .university(studentById.getUniversity())
                .emailId(studentById.getEmail())//TODO: The email validation.
                .build();
    }

    public StudentPgDto convertWithPg(Student studentById) {
        return StudentPgDto.builder()
                //TODO:
                .build();
    }
}