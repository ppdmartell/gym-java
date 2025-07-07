package com.example.mongodb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.mongodb.exception.ResourceNotFoundException;
import com.example.mongodb.exception.StudentAlreadyExists;
import com.example.mongodb.exception.StudentsListIsEmpty;
import com.example.mongodb.model.Student;
import com.example.mongodb.repository.StudentRepository;
import com.example.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Student> createStudentsInBulk(List<Student> students) {
        this.isStudentListEmpty(students);
        this.checkIfAnyStudentExists(students);
        return studentRepository.saveAll(students);
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
        if(students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name);
        return students;
    }

    @Override
    public List<Student> getStudentByNameNativeWay(String name) {
        List<Student> students = studentRepository.getByName(name);
        if(students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name);
        return students;
    }

    @Override
    public List<Student> getStudentsNameStartsWith(String word) {
        List<Student> students = studentRepository.findByNameStartingWith(word);
        if(students.isEmpty()) throw new ResourceNotFoundException("No student's name starts with: " + word);
        return students;
    }

    @Override
    public List<Student> getStudentsByDepartmentName(String name) {
        List<Student> students = studentRepository.findByDepartmentName(name);
        if(students.isEmpty()) throw new ResourceNotFoundException("Department not found for name: " + name);
        return students;
    }

    @Override
    public List<Student> getStudentsBySubjectName(String name) {
        List<Student> students = studentRepository.findBySubjectsName(name);
        if(students.isEmpty()) throw new ResourceNotFoundException("No subject found with name: " + name);
        return students;
    }

    @Override
    public List<Student> getStudentsByEmailLike(String like) {
        List<Student> students = studentRepository.findByEmailLike(like);
        if(students.isEmpty()) throw new ResourceNotFoundException("No student was found with a portion of email like: " + like);
        return students;
    }

    @Override
    public List<Student> findByNameAndEmail(String name, String email) {
        List<Student> students = studentRepository.findByNameAndEmail(name, email);
        if(students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name + " and email: " + email);
        return students;
    }

    @Override
    public List<Student> findByNameOrEmail(String name, String email) {
        List<Student> students = studentRepository.findByNameOrEmail(name, email);
        if(students.isEmpty()) throw new ResourceNotFoundException("No student found with name: " + name + " or email: " + email);
        return students;
    }

    /* Use getContent() over toList() if only want to read, since toList():
    *   Converts the entire Page<Student> (which is an iterable) to a new List.
    *   Internally calls StreamSupport.stream(...).collect(Collectors.toList()).
    *   Slightly less efficient than getContent() because it doesn't leverage the Page interface directly.
    *   Still works, but more generic, and not the idiomatic Spring way for paged results.
    * */
    @Override
    public List<Student> getStudentsPaginated(int pageNo, int pageSize) {
        if(pageNo <= 0 || pageSize <= 0) throw new IllegalArgumentException("Page number and page size can't be zero or negative.");
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Student> getStudentsSortedByName() {
        if(checkIfAnyStudentExists()) throw new StudentsListIsEmpty("No student at all was found in the database.");
        Sort sorter = Sort.by(Sort.Direction.ASC, "name");
        return studentRepository.findAll(sorter);
    }

    //PRIVATE METHODS
    private void checkIfAnyStudentExists(List<Student> students) {
        for(Student s : students) {
            if(studentRepository.getStudentByEmail(s.getEmail()).isPresent()) throw new StudentAlreadyExists("Student from the list already exists.");
        }
    }

    private boolean checkIfAnyStudentExists() {
        return studentRepository.findAll().isEmpty();
    }

    private void isStudentListEmpty(List<Student> students) {
        if(students.isEmpty()) throw new StudentsListIsEmpty("Students list to be created can't be empty.");
    }
}
