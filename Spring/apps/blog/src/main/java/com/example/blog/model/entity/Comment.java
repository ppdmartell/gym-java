package com.example.blog.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Comment {

    @Id
    private String id;
    private String postId; // Use it when not using DBRef

    @CreatedDate // For Spring Data MongoDB to autofill it. Requires MongoConfig class in config package
    private LocalDateTime createdDate;
    private String username; // In principle should be retrieved automatically, for now manually
    private String text;

    public Comment(String postId, String text, String username) {
        this.postId = postId;
        this.text = text;
        this.username = username;
    }

    public Comment() {}

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }
}
