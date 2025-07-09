package com.example.blog.repository;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<CommentDto> findByPostId(String postId);

    List<CommentDto> findByPostIdOrderByCreatedDateAsc(String postId);

    List<CommentDto> findByPostIdOrderByCreatedDateDesc(String postId);
}
