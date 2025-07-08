package com.example.blog.mapper;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;
import com.example.blog.model.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(NewCommentDto newCommentDto) {
        return new Comment(newCommentDto.postId(), newCommentDto.text(), newCommentDto.username());
    }

    public CommentDto toCommentDto(Comment comment) {
        return new CommentDto(comment.getId(),
                comment.getPostId(),
                comment.getText(),
                comment.getUsername(),
                comment.getCreatedDate());
    }
}
