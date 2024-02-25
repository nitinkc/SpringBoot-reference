package com.springHelloWorld.controller;

import com.springHelloWorld.dto.StudentDto;
import com.springHelloWorld.dto.StudentDtoClass;
import com.springHelloWorld.dto.StudentRequestBody;
import com.springHelloWorld.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController(value = "Rest controller for student")
@RequestMapping("/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/{studentId}", path = "/{studentId}",
            consumes = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = { "application/json", MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public StudentDto getStudentById(@PathVariable String studentId){
        int studentIntId = Integer.valueOf(studentId);
        StudentDto studentDetailById = studentService.getStudentById(studentIntId);
        return studentDetailById;
    }

    @RequestMapping(path = "/requestMapping/{studentId}",
            produces = { "application/json", MediaType.APPLICATION_XML_VALUE,  MediaType.APPLICATION_PDF_VALUE})
    public @ResponseBody StudentDto getStudentByIdRequestMapping(@PathVariable String studentId){
        int studentIntId = Integer.valueOf(studentId);
        StudentDto studentDetailById = studentService.getStudentById(studentIntId);
        return studentDetailById;
    }

    @PostMapping(path = "/studentIdsByMap",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {"application/json"})
    public List<StudentDto> getStudentByIdsByMap(@RequestBody Map<String,List<Integer>> mapStudentIds){
        List<Integer> studentIdList = mapStudentIds.get("studentIds");
        List<StudentDto> studentDetailById = studentService.getStudentByIds(studentIdList);
        return studentDetailById;
    }

    @PostMapping(path = "/studentIdsByClassName",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {"application/json"})
    public StudentDtoClass getStudentByIdsRequestBody(@RequestBody StudentRequestBody studentRequestBody){

        logger.trace(studentRequestBody.getGreeting());//TODO: Server debugging.
        List<String> studentIdStringList = studentRequestBody.getStudentIdList();
        System.out.println(studentIdStringList);
        List<Integer> studentIdList = studentIdStringList
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<StudentDto> studentDetailById = studentService.getStudentByIds(studentIdList);
        StudentDtoClass studentDtoClassReturn = StudentDtoClass.builder()
                .count(studentDetailById.size())
                .studentDtoList(studentDetailById)
                .build();

        return studentDtoClassReturn;
    }
}
