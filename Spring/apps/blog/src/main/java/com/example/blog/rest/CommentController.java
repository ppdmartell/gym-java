package com.example.blog.rest;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;
import com.example.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /* You need to use the name of each field as-is in the @PathVariable, otherwise it will get null
    *  such is the case with postId. I was using "postid" in postman and postId was getting picked up
    *  as null. And due to this it was being persisted as null as well in the database.
    *
    * {
        "postId": "686d905d5c8fca1ef7ef70c4",
        "text": "I really like this post.",
        "username": "elon"
        }
    * */

    @PostMapping("/create")
    public ResponseEntity<CommentDto> addCommentToPost(@PathVariable String postId, @RequestBody NewCommentDto newCommentDto) {
        CommentDto commentDto = commentService.addCommentToPost(postId, newCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }
}
