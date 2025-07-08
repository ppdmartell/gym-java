package com.example.blog.service;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;

public interface CommentService {

    CommentDto addCommentToPost(String postId, NewCommentDto newCommentDto);
}
