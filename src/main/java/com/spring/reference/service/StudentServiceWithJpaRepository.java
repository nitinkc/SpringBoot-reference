package com.spring.reference.service;

import com.spring.reference.dao.repository.StudentJpaRepository;
import com.spring.reference.dto.StudentDto;
import com.spring.reference.dto.StudentSave;
import com.spring.reference.exception.business.StudentNotFoundException;
import com.spring.reference.mapper.StudentMapper;
import com.spring.reference.model.Student;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceWithJpaRepository {

    private StudentJpaRepository studentJpaRepository;
    @Autowired
    private StudentMapper studentMapper;//Field Injection

    public StudentServiceWithJpaRepository(@Qualifier("jpaStudentRepository") StudentJpaRepository studentJpaRepository) {//Constructor injection
        this.studentJpaRepository = studentJpaRepository;
    }

    public StudentDto getStudentById(int studentId)  {
        //Method from JPA Repo, returns Optional
        Optional<Student> studentById = studentJpaRepository.findById(studentId);

        Student student = studentById.orElseThrow(() -> new StudentNotFoundException("The Student with Id "
                + studentId  + " does not exist")); //Return empty constructor if no data/Null Or
        //Throw custom Exception, in case business requirement is not to return empty data

        //Convert the DB Model into Response DTO
        StudentDto studentDto = studentMapper.convert(student);//Convertor/Mapper/Transformer
        return studentDto;
    }

    public List<StudentDto> getStudentByIds(List<Integer> studentIdList) {
        List<Student> studentDetailsList = studentJpaRepository.findAllById(studentIdList);
        List<StudentDto> studentDtoList = studentDetailsList.stream()
                .map(studentMapper::convert)
                .collect(Collectors.toList());
        return studentDtoList;
    }

    public int saveStudent(StudentSave studentSave){
        Student student = Student.builder()
                //.id(4000)// If Primary key is provided, it behaves like an update statement
                .id(studentJpaRepository.getNextSequence())//Introducing Sequence
                .firstName(studentSave.firstName())
                .lastName(studentSave.lastName())
                .cityofbirth(studentSave.cityofbirth())
                .dob(studentSave.dob())
                .email(studentSave.email())
                .gender(studentSave.gender())
                .university(studentSave.university())
                .build();

        Student savedStudent = studentJpaRepository.save(student);
        return savedStudent.getId();
    }
}
