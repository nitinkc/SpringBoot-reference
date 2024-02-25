package com.springHelloWorld.service;

import com.springHelloWorld.dao.pg.PgJdbcTemplate;
import com.springHelloWorld.dao.repository.StudentRepository;
import com.springHelloWorld.dto.StudentPGRequestBody;
import com.springHelloWorld.dto.StudentPgDto;
import com.springHelloWorld.mapper.StudentMapper;
import com.springHelloWorld.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StudentServiceWithJdbcTemplatePg {
    private StudentRepository studentRepository;
    private StudentMapper mapper;

    private PgJdbcTemplate pgJdbcTemplate;


    @Autowired
    public StudentServiceWithJdbcTemplatePg(@Qualifier("jpaStudentRepository") StudentRepository studentRepository, StudentMapper mapper, PgJdbcTemplate pgJdbcTemplate) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
        this.pgJdbcTemplate = pgJdbcTemplate;
    }

    public List<StudentPgDto> getStudentById()  {
        List<Student> maleStudents = studentRepository.findMaleStudents();

        //Convert the DB Model into Response DTO
        /*List<StudentPgDto> studentPgDtos = maleStudents.stream()
                //.map()//TODO:
                .collect(Collectors.toList());
*/
        return null;
    }

    public Student getStudentByIdPgJdbc(int id){
        Student student = pgJdbcTemplate.runQueryAndGetResult(id);

        return student;

    }
    public Student saveSingleStudentPgJdbc(StudentPGRequestBody studentPGRequestBody){
        Student sDetail = Student.builder()
                .id(studentRepository.getNextSequence())
                .firstName(studentPGRequestBody.getFirstName())
                .lastName(studentPGRequestBody.getLastName())
                .gender(studentPGRequestBody.getGender())
                .cityofbirth(studentPGRequestBody.getCityofbirth())
                .email(studentPGRequestBody.getFirstName())
                .university(studentPGRequestBody.getUniversity())
                .dob(studentPGRequestBody.getDob())
                .build();

        Student savedStudent = pgJdbcTemplate.saveSingleStudentAndGetResult(sDetail);
        return savedStudent;
    }
}