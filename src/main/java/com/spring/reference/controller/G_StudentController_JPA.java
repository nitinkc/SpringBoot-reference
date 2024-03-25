package com.spring.reference.controller;

import com.spring.reference.dto.StudentDto;
import com.spring.reference.dto.StudentDtoClass;
import com.spring.reference.dto.StudentRequestBody;
import com.spring.reference.dto.StudentSave;
import com.spring.reference.exception.business.DbDownException;
import com.spring.reference.exception.business.StudentNotFoundException;
import com.spring.reference.exception.business.TraceableBusinessException;
import com.spring.reference.service.StudentServiceWithJpaRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController(value = "Rest controller for student with Db")
@RequestMapping("/student/jpa")
public class G_StudentController_JPA {

    private StudentServiceWithJpaRepository studentServiceWithJpaRepository;

    @Autowired
    public G_StudentController_JPA(StudentServiceWithJpaRepository studentServiceWithJpaRepository) {
        this.studentServiceWithJpaRepository = studentServiceWithJpaRepository;
    }

    @GetMapping(value = "/{studentId}", path = "/{studentId}",
            produces = { "application/json", MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public StudentDto getStudentById(@PathVariable String studentId) throws
            StudentNotFoundException, DbDownException, TraceableBusinessException {
        int studentIntId = Integer.valueOf(studentId);
        if(true) {
            //throw new StudentNotFoundException("Student not found");
            throw new DbDownException("DB is Down");
            //throw new IllegalArgumentException("test");
            //throw new SomeBusinessException("Some Business Exception");
        }
        StudentDto studentDetailById = studentServiceWithJpaRepository.getStudentById(studentIntId);
        return studentDetailById;
    }

    @RequestMapping(path = "/requestMapping/{studentId}",
            method = RequestMethod.GET,
            produces = { "application/json", MediaType.APPLICATION_XML_VALUE,  MediaType.APPLICATION_PDF_VALUE})
    public @ResponseBody StudentDto getStudentByIdRequestMapping(@PathVariable String studentId) throws StudentNotFoundException {
        int studentIntId = Integer.valueOf(studentId);
        StudentDto studentDetailById = studentServiceWithJpaRepository.getStudentById(studentIntId);
        return studentDetailById;
    }

    @PostMapping(path = "/studentIdsByMap",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {"application/json"})
    public List<StudentDto> getStudentByIdsByMap(@RequestBody Map<String,List<Integer>> mapStudentIds) throws StudentNotFoundException {
        List<Integer> studentIdList = mapStudentIds.get("studentIds");
        List<StudentDto> studentDetailById = studentServiceWithJpaRepository.getStudentByIds(studentIdList);
        return studentDetailById;
    }

    @PostMapping(path = "/studentIdsByClassName",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {"application/json"})
    public StudentDtoClass getStudentByIdsRequestBody(@RequestBody StudentRequestBody studentRequestBody){

        List<String> studentIdStringList = studentRequestBody.getStudentIdList();
        System.out.println(studentIdStringList);
        List<Integer> studentIdList = studentIdStringList
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<StudentDto> studentDetailById = studentServiceWithJpaRepository.getStudentByIds(studentIdList);
        StudentDtoClass studentDtoClassReturn = StudentDtoClass.builder()
                .count(studentDetailById.size())
                .studentDtoList(studentDetailById)
                .build();

        return studentDtoClassReturn;
    }

    @PutMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json")
    public int saveStudent(@RequestBody StudentSave student) {
        return studentServiceWithJpaRepository.saveStudent(student);
    }
}
