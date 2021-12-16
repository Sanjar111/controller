package com.student.control.repositories;

import com.student.control.exceptions.EtBadRequestException;
import com.student.control.exceptions.EtResourceNotFoundException;
import com.student.control.model.Student;

import java.util.List;

public interface StudentRepository {

    List<Student> findAll() throws EtResourceNotFoundException;

    Student findById(Integer studentId) throws EtResourceNotFoundException;

    Integer create(String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException;

    void update(Integer studentId,String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException;

    void removeCustomerById(Integer studentId);



}


