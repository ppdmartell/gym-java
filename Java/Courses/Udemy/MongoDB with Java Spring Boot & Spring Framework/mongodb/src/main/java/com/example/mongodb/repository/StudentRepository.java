package com.example.mongodb.repository;

import com.example.mongodb.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// MongoRepository<T, ID>, being T the data type (Student), and ID the id's type (String)
// of the data type Student.

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
