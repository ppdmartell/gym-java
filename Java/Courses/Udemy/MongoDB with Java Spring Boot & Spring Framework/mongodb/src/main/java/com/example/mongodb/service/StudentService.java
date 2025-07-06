package com.example.mongodb.service;

import java.util.List;

import com.example.mongodb.model.Student;

public interface StudentService {

    Student createStudent(Student student);
    Student updateStudent(Student student);
    String deleteStudent(String id);
    Student getStudentById(String id);
    List<Student> getAllStudents();
    List<Student> getStudentsByName(String name);
}
