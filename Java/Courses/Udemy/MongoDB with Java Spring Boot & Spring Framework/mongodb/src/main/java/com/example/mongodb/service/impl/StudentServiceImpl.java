package com.example.mongodb.service.impl;

import com.example.mongodb.exception.ResourceNotFoundException;
import com.example.mongodb.model.Student;
import com.example.mongodb.repository.StudentRepository;
import com.example.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    // Field injection with @Autowired is discouraged
    // Using constructor injection instead of field injection (@Autowired)
    // for better testability, immutability, and clearer dependency management.
    // Field injection is discouraged because it hides dependencies and complicates unit testing
    private final StudentRepository studentRepository;

    @Autowired  // Optional if there's only one constructor, mandatory if there is more. Still a good practice to keep it
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found for id: " + id));
    }
}
