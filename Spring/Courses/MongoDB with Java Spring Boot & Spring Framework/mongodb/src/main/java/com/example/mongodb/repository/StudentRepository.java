package com.example.mongodb.repository;

import com.example.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// MongoRepository<T, ID>, being T the data type (Student), and ID the id's type (String)
// of the data type Student.

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findAllByName(String name);
    List<Student> findByNameAndEmail(String name, String email);
    List<Student> findByNameOrEmail(String name, String email);
    Optional<Student> getStudentByEmail(String email);
    List<Student> findByDepartmentName(String departmentName);
    List<Student> findBySubjectsName(String subjectsName);
    List<Student> findByEmailLike(String email);
    List<Student> findByNameStartingWith(String word);

    @Query ("{ \"name\" : \"?0\"}")
    List<Student> getByName(String name);
}
