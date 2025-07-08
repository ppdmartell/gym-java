package com.example.blog.rest;

import com.example.blog.model.dto.post.NewPostDto;
import com.example.blog.model.dto.post.PostDto;
import com.example.blog.model.entity.Post;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
    * The idea is to follow a receive:NewPostDto, persist:Post and return:PostDto
    * So you can hide data from API response (e.g. username that created the post)
    * and also them 3 (dtos and the entity) can evolve on their own.
    * When giving a response back, you can use the respective mapper to convert
    * to PostDto and decide which field you are keeping from returning.
    *
    * The mapping and persist call shold be done in the service layer and keep the
    * controller as thin as possible: thin: they handle HTTP request/response,
    * validation, status codes. Controllers should be focused on we concerns only.
    *
    * Business logic and data transformations (like mapping between entities and DTOs)
    * belong in the service layer.
    *
    * General flow:
    * Receive -> NewPostDto
    * Persist -> Post (service layer)
    * Return  -> PostDto (using mapper first to convert from Post to PostDto) (service layer)
    *
    * https://chatgpt.com/share/686d7319-d118-8002-9882-05ee8fc3116f
    *
    * */
    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody NewPostDto newPostDto) {
        PostDto postDto = postService.createPost(newPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }
}
