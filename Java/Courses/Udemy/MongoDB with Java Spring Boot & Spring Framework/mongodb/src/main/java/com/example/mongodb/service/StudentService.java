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
    List<Student> findByNameAndEmail(String name, String email);
    List<Student> findByNameOrEmail(String name, String email);
    List<Student> createStudentsInBulk(List<Student> students);
    List<Student> getStudentsPaginated(int pageNo, int pageSize);
    List<Student> getStudentsSortedByName();
    List<Student> getStudentsByDepartmentName(String name);
    List<Student> getStudentsBySubjectName(String name);
    List<Student> getStudentsByEmailLike(String like);
    List<Student> getStudentsNameStartsWith(String word);
}
