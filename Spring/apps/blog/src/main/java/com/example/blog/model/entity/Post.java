package com.example.blog.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Post(String id, String title) {}
