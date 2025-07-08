package com.example.blog.model.dto.post;

import java.time.LocalDateTime;

public record PostDto(String id, String title, String body, LocalDateTime createdDate, String updatedDate) {}