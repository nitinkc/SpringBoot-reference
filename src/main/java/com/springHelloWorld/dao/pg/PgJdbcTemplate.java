package com.springHelloWorld.dao.pg;

import com.springHelloWorld.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class PgJdbcTemplate{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    final String INSERT_QUERY = """
            insert into student (id, first_name, last_name, gender, cityofbirth, email, university, dob) 
            values (?,?,?,?,?,?,?,?) --Query Parameters     
            RETURNING id
            """;

    public Student runQueryAndGetResult(int id) {

        Student student = null;
        try {
            student = jdbcTemplate.queryForObject("SELECT * FROM student WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Student.class), id);

        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println(e.getMessage());
            ;
        }
        return student;
    }

    public Student saveSingleStudentAndGetResult(Student student){
        /*PreparedStatementCreator psc = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3,student.getLastName());
            //TODO: Finish the last

            return preparedStatement;
        };*/

        Boolean execute = false;

        try {
            execute = (Boolean) jdbcTemplate.execute(INSERT_QUERY,
                    (PreparedStatementCallback<Object>) (preparedStatement) -> {
                        preparedStatement.setInt(1, student.getId());
                        preparedStatement.setString(2, student.getFirstName());
                        preparedStatement.setString(3, student.getLastName());
                        preparedStatement.setString(4, student.getGender());
                        preparedStatement.setString(5, student.getCityofbirth());
                        preparedStatement.setString(6, student.getEmail());
                        preparedStatement.setString(7, student.getUniversity());
                        preparedStatement.setDate(8, (Date) student.getDob());

                        return preparedStatement.execute();
                    });
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(execute){
            return student;
        }
        return Student.builder().build();

    }
}
