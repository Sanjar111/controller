package com.student.control.services;

import com.student.control.exceptions.EtBadRequestException;
import com.student.control.exceptions.EtResourceNotFoundException;
import com.student.control.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> fetchAllCategories();
    Student  fetchCategoryById(Integer studentId) throws EtResourceNotFoundException;

    Student addCategory(String firstName, String lastName,Integer age, Integer course, Boolean status) throws EtBadRequestException;

    void updateCategory(Integer studentId,String firstName, String lastName,Integer age, Integer course, Boolean status)throws EtBadRequestException;

    void removeCustomerById(Integer studentId) throws EtResourceNotFoundException;


    Student validateStudent(String email, String password);

    Student registerStudent(String firstName, String lastName, String email, String password);
}
