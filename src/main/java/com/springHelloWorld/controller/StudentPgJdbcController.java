package com.springHelloWorld.controller;

import com.springHelloWorld.dto.StudentPGRequestBody;
import com.springHelloWorld.model.Student;
import com.springHelloWorld.service.StudentServiceWithJdbcTemplatePg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc")
public class StudentPgJdbcController {

    private StudentServiceWithJdbcTemplatePg studentServiceWithJdbcTemplatePg;

    @Autowired
    public StudentPgJdbcController(StudentServiceWithJdbcTemplatePg studentServiceWithJdbcTemplatePg) {
        this.studentServiceWithJdbcTemplatePg = studentServiceWithJdbcTemplatePg;
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getTutorialById(@PathVariable("id") int id) {
        Student student = studentServiceWithJdbcTemplatePg.getStudentByIdPgJdbc(id);

        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/student/save")
    public Student saveSingleStudentJdbc(@RequestBody StudentPGRequestBody studentPGRequestBody){
        Student student = studentServiceWithJdbcTemplatePg.saveSingleStudentPgJdbc(studentPGRequestBody);
        return student;
    }

//    @PatchMapping("/student/patch/{id}")

}
