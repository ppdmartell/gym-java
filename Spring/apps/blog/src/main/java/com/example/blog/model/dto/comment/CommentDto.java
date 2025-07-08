package com.example.blog.model.dto.comment;

import java.time.LocalDateTime;

public record CommentDto(String id, String comment, String author, LocalDateTime creationDate) {}
