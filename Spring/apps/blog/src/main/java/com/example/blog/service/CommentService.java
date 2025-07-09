package com.example.blog.service;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addCommentToPost(String postId, NewCommentDto newCommentDto);
    List<CommentDto> getAllComments(String postId);
    List<CommentDto> getAllCommentsSortedAsc(String postId);
    List<CommentDto> getAllCommentsSortedDesc(String postId);
}
