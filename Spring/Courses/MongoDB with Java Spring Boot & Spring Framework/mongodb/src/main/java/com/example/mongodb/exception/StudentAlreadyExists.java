package com.example.mongodb.exception;

public class StudentAlreadyExists extends RuntimeException {
    public StudentAlreadyExists(String message) {
        super(message);
    }
}
