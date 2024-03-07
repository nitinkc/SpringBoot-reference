package com.spring.reference.service;

import com.spring.reference.dao.pg.PgJdbcTemplate;
import com.spring.reference.dao.repository.StudentJpaRepository;
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
    private StudentJpaRepository studentJpaRepository;
    private StudentMapper mapper;

    private PgJdbcTemplate pgJdbcTemplate;


    @Autowired
    public StudentServiceWithJdbcTemplatePg(@Qualifier("jpaStudentRepository") StudentJpaRepository studentJpaRepository, StudentMapper mapper, PgJdbcTemplate pgJdbcTemplate) {
        this.studentJpaRepository = studentJpaRepository;
        this.mapper = mapper;
        this.pgJdbcTemplate = pgJdbcTemplate;
    }

    public List<StudentPgDto> getStudentById()  {
        List<Student> maleStudents = studentJpaRepository.findMaleStudents();

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
                .id(studentJpaRepository.getNextSequence())
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