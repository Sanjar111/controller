package com.student.control.services;

import com.student.control.exceptions.EtBadRequestException;
import com.student.control.exceptions.EtResourceNotFoundException;
import com.student.control.model.Student;
import com.student.control.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements  StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> fetchAllCategories() {
        return studentRepository.findAll();
    }

    @Override
    public Student fetchCategoryById(Integer StudentId) throws EtResourceNotFoundException {
        return studentRepository.findById(StudentId);
    }

    @Override
    public Student addCategory(String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException {
        int customerId = studentRepository.create(firstName,lastName,age,course,status);
        return studentRepository.findById(customerId);
    }

    @Override
    public void updateCategory(Integer studentId, String firstName, String lastName, Integer age, Integer course, Boolean status) throws EtBadRequestException {
    studentRepository.update(studentId,firstName,lastName,age,course,status);
    }

    @Override
    public void removeCustomerById(Integer studentId) throws EtResourceNotFoundException {
    this.fetchCategoryById(studentId);
    studentRepository.removeCustomerById(studentId);
    }


    }

