package com.example.blog.service.impl;

import com.example.blog.mapper.PostMapper;
import com.example.blog.model.dto.post.NewPostDto;
import com.example.blog.model.dto.post.PostDto;
import com.example.blog.model.entity.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public PostDto createPost(NewPostDto newPostDto) {
        Post post = postMapper.toEntity(newPostDto);
        postRepository.save(post);
        return postMapper.toPostDto(post);
    }
}
