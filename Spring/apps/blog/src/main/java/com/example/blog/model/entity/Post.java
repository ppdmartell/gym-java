package com.example.blog.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Post {

    @Id
    private String id;
    private String title;
    private String body;

    @CreatedDate // For Spring Data MongoDB to autofill it. Requires MongoConfig class in config package
    private LocalDateTime createdDate;

    @LastModifiedDate  // For Spring Data MongoDB to autofill it. Requires MongoConfig class in config package
    private LocalDateTime updatedDate;

}
