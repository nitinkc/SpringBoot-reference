package com.spring.reference.controller;

import com.spring.reference.dto.StudentPGRequestBody;
import com.spring.reference.model.Student;
import com.spring.reference.service.StudentServiceWithJdbcTemplatePg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jdbc")
public class H_StudentController_PgJdbc {

    private StudentServiceWithJdbcTemplatePg studentServiceWithJdbcTemplatePg;

    @Autowired
    public H_StudentController_PgJdbc(StudentServiceWithJdbcTemplatePg studentServiceWithJdbcTemplatePg) {
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
