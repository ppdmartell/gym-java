package com.example.mongodb.service.impl;

import java.util.List;

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
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudent(String id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(("Student not found for id: " + id)));
        studentRepository.delete(student);
        return "Student with id: " + id + "has been deleted.\n" + student;
    }

    @Override
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found for id: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    /*
    * Method studentRepository.findAllByName(name) can be automatically created using the IDE capabilities
    * Please note, the method was created in StudentRepository interface that extends MongoRepository<Student, String>
    * Because by default it appears it doesn't come, so this is a capability offered by MongoRepository interface (JPA)
    */
    @Override
    public List<Student> getStudentsByName(String name) {
        List<Student> students = studentRepository.findAllByName(name);
        if (students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name);
        return students;
    }

    @Override
    public List<Student> findByNameAndEmail(String name, String email) {
        List<Student> students = studentRepository.findByNameAndEmail(name, email);
        if (students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name + " and email: " + email);
        return students;
    }

    @Override
    public List<Student> findByNameOrEmail(String name, String email) {
        List<Student> students = studentRepository.findByNameOrEmail(name, email);
        if (students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name + " or email: " + email);
        return students;
    }
}
