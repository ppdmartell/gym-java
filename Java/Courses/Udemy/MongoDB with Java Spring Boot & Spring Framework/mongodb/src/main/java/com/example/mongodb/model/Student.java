package com.example.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "student")
public class Student {

    @Id
    private String id;
    private String name;
    private String email;

    private Department department;
    private List<Subjects> subjects;

}
