package com.example.blog.service.impl;

import com.example.blog.model.dto.post.PostDto;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto newPost) {
        return postRepository.save(newPost);
    }
}
