package com.example.blog.model.dto.post;

import com.example.blog.model.dto.comment.CommentDto;

import java.util.List;

public record NewPostDto(String id, String title, String body, String createdDated, List<CommentDto> comments) {}
