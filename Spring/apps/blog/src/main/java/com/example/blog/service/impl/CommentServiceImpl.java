package com.example.blog.service.impl;

import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;
import com.example.blog.model.entity.Comment;
import com.example.blog.model.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, PostRepository postRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto addCommentToPost(String postId, NewCommentDto newCommentDto) {
        Post post = postRepository.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("No post found for id: " + postId));

        Comment comment = commentMapper.toEntity(newCommentDto);
        commentRepository.save(comment);
        return commentMapper.toCommentDto(comment);

    }
}
