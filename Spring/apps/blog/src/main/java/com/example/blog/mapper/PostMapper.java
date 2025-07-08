package com.example.blog.mapper;

import com.example.blog.model.dto.post.NewPostDto;
import com.example.blog.model.dto.post.PostDto;
import com.example.blog.model.entity.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


// This is manual mapper, consider using MapStruct
@Component
public class PostMapper {

    public Post toEntity(NewPostDto newPostDto) {
        return new Post(newPostDto.title(), newPostDto.body()); // The rest of fields are null by default
    }

    public PostDto toPostDto(Post post) {
        return new PostDto(post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getCreatedDate(),
                post.getUpdatedDate() == null ? "Not yet updated" : post.getUpdatedDate().toString() );
    }
}
