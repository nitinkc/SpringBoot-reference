package com.spring.reference.service;

import com.spring.reference.dao.pg.PgJdbcTemplate;
import com.spring.reference.dao.repository.StudentRepository;
import com.spring.reference.dto.StudentPGRequestBody;
import com.spring.reference.dto.StudentPgDto;
import com.spring.reference.mapper.StudentMapper;
import com.spring.reference.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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