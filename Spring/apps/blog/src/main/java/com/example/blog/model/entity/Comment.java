package com.example.blog.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Comment {

    @Id
    private String id;
    private String postId;

    @CreatedDate // For Spring Data MongoDB to autofill it. Requires MongoConfig class in config package
    private LocalDateTime createdDate;
    private String username;
    private String comment;
}
