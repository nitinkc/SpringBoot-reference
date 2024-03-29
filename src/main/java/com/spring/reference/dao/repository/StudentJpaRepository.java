package com.spring.reference.dao.repository;

import com.spring.reference.model.Student;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@Qualifier("jpaStudentRepository")
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

    // Custom query - ONLY for Simple Queries,
    @Query(nativeQuery = true, value = "SELECT * from student s WHERE s.gender = null")
    List<Student> findMaleStudents();

    // Custom query using JPQL - Java Persistence Query Language
    @Query("SELECT s FROM Student s WHERE s.gender = 'Female'")
    List<Student> findFemaleStudentsJpql();

    // Custom query, JPQL, with Query Parameter
    @Query("SELECT s FROM Student s WHERE s.dob > :date")
    List<Student> findByDobDateAfter(@Param("date") LocalDate date);

    @Query(nativeQuery = true, value = "SELECT nextval('student_seq');")
    int getNextSequence();
}
