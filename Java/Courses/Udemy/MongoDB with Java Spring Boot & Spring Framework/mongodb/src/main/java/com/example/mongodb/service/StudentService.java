package com.example.mongodb.service;

import java.util.List;

import com.example.mongodb.model.Student;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudentById(String id);
    List<Student> getAllStudents();
}
