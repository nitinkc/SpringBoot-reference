package com.spring.reference.service;

import com.spring.reference.model.Student;
import com.spring.reference.dao.repository.StudentRepositoryDummyData;
import com.spring.reference.dto.StudentDto;
import com.spring.reference.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired StudentRepositoryDummyData studentRepositoryDummyData;
    @Autowired StudentMapper studentMapper;

    public StudentDto getStudentById(int studentId){
        Student studentById = studentRepositoryDummyData.getStudentById(studentId);
        StudentDto studentDto = studentMapper.convert(studentById);

        return studentDto;
    }

    public List<StudentDto> getStudentByIds(List<Integer> studentIdList) {
        List<Student> studentDetailsList = studentRepositoryDummyData.getMultipleStudentByIds(studentIdList);

        //Intuitive way
        /*List<StudentDto> studentDtoList = new ArrayList<>();
        for(Student s:studentDetailsList){
            StudentDto singleStudentDto = studentMapper.convert(s);
            studentDtoList.add(singleStudentDto);
        }*/

        //Java 8
        List<StudentDto> studentDtoList = studentDetailsList.stream()
                //.filter(Objects::nonNull)//Remove any null rows if needed
                .map(studentMapper::convert)
                .collect(Collectors.toList());

        return studentDtoList;
    }
}
