package com.example.blog.mapper;

import com.example.blog.model.dto.post.NewPostDto;
import com.example.blog.model.entity.Post;
import org.springframework.stereotype.Component;


// This is manual mapper, consider using MapStruct
@Component
public class PostMapper {

    public Post toEntity(NewPostDto postDto) {
        return new Post(postDto.);
    }
}
