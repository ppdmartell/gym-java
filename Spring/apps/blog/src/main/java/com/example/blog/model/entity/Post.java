package com.example.blog.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

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

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post() {}

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getTitle(), post.getTitle()) && Objects.equals(getBody(), post.getBody()) && Objects.equals(getCreatedDate(), post.getCreatedDate()) && Objects.equals(getUpdatedDate(), post.getUpdatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBody(), getCreatedDate(), getUpdatedDate());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
