package com.example.mongodb.handler;

import com.example.mongodb.exception.ResourceNotFoundException;
import com.example.mongodb.exception.StudentAlreadyExists;
import com.example.mongodb.exception.StudentsListIsEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*
    * ResourceNotFoundException exception class doesn't come built-in, it must be implemented
    * manually. This handler will return 404 as HTTP response.
    * */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(StudentAlreadyExists.class)
    public ResponseEntity<String> handleStudentAlreadyExists(StudentAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(StudentsListIsEmpty.class)
    public ResponseEntity<String> handleStudentsListIsEmpty(StudentsListIsEmpty ex) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
