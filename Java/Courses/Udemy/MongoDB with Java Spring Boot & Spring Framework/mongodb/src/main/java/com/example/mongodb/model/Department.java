package com.example.mongodb.model;

// No need to mark it as @Document because at database-level, Department is not a separate collection
// There is only one collection and it's student.
// Although, Department could be created as a separate collection if desired.
public class Department {

    private String name;
    private String location;

}
