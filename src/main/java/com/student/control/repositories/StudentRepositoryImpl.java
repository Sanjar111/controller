package com.student.control.repositories;

import com.student.control.exceptions.EtBadRequestException;
import com.student.control.exceptions.EtResourceNotFoundException;
import com.student.control.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static final String SQL_FIND_ALL =  "SELECT * FROM STUDENT";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM STUDENT WHERE STUDENT_ID=?";
    private static final String SQL_CREATE = "INSERT INTO STUDENT (STUDENT_ID,FIRST_NAME,LAST_NAME,AGE,COURSE,STATUS) VALUES(NEXTVAL('STUDENT_SEQ'),?,?,?,?,?)";
    private static final String SQL_UPDATE =  "UPDATE STUDENT SET FIRST_NAME = ?, LAST_NAME = ?, AGE = ?, COURSE = ?, STATUS = ? WHERE STUDENT_ID = ?";
    private static final String SQL_DELETE_CUSTOMER ="DELETE FROM STUDENT WHERE STUDENT_ID=?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query((String) SQL_FIND_ALL,studentRowMapper);
    }

    @Override
    public Student findById(Integer studentId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, studentRowMapper, new Object[]{studentId});
        }catch (Exception e){
            throw new EtResourceNotFoundException("Student not found");
        }
    }

    @Override
    public Integer create(String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException {
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection-> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,firstName);
                ps.setString(2,lastName);
                ps.setInt(3,age);
                ps.setInt(4,course);
                ps.setBoolean(5,status);
                return  ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("STUDENT_ID");
        }catch (Exception e){
            throw new EtBadRequestException("Invalid request");
        }
    }

    @Override
    public void update(Integer studentId, String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException {
    try {
        jdbcTemplate.update(SQL_UPDATE,new Object[]{firstName, lastName, age, course, status,  studentId});
    }catch (Exception e){
        throw  new EtBadRequestException("Invalid request update");
    }
    }

    @Override
    public void removeCustomerById(Integer studentId) {
     jdbcTemplate.update(SQL_DELETE_CUSTOMER, new Object[]{studentId});
     
    }
    // private void removeAllCustomers() {
    //     jdbcTemplate.update(SQL_DELETE_ALL_CUSTOMER});
    // }
    private RowMapper<Student> studentRowMapper = ((rs, rowNum) -> {
        return new Student
                (rs.getInt("STUDENT_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getInt("AGE"),
                rs.getInt("COURSE"),
                rs.getBoolean("STATUS"),
                        rs.getString("TIME"));

    });
}
