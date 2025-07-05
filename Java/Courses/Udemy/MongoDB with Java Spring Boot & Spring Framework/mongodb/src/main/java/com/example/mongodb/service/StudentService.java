package com.example.mongodb.service;

import com.example.mongodb.model.Student;

public interface StudentService {

    Student createStudent(Student student);
    Student getStudentById(String id);
}
