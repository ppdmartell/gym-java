package com.example.mongodb.exception;

public class StudentsListIsEmpty extends RuntimeException {
    public StudentsListIsEmpty(String message) {
        super(message);
    }
}
