package com.example.blog.service;

import com.example.blog.model.dto.post.NewPostDto;
import com.example.blog.model.dto.post.PostDto;

public interface PostService {

    PostDto createPost(NewPostDto newPostDto);
}
