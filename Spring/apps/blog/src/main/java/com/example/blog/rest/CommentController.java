package com.example.blog.rest;

import com.example.blog.model.dto.comment.CommentDto;
import com.example.blog.model.dto.comment.NewCommentDto;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    *
    *  This method adds a comment to an existing post.
    * */
    @PostMapping("/create")
    public ResponseEntity<CommentDto> addCommentToPost(@PathVariable String postId, @RequestBody NewCommentDto newCommentDto) {
        CommentDto commentDto = commentService.addCommentToPost(postId, newCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    /* This method returns all the comments that belong to a certain post
    */
    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable String postId) {
        List<CommentDto> comments = commentService.getAllComments(postId);
        return ResponseEntity.status(HttpStatus.FOUND).body(comments);
    }

    /* This method returns all the comments that belong to a certain post in ascending order
     */
    @GetMapping("/all/asc")
    public ResponseEntity<List<CommentDto>> getAllCommentsSortedAsc(@PathVariable String postId) {
        List<CommentDto> comments = commentService.getAllCommentsSortedAsc(postId);
        return ResponseEntity.status(HttpStatus.FOUND).body(comments);
    }

    /* This method returns all the comments that belong to a certain post in descending order
     */
    @GetMapping("/all/desc")
    public ResponseEntity<List<CommentDto>> getAllCommentsSortedDesc(@PathVariable String postId) {
        List<CommentDto> comments = commentService.getAllCommentsSortedDesc(postId);
        return ResponseEntity.status(HttpStatus.FOUND).body(comments);
    }
}
