package com.example.blog.repository;

import com.example.blog.model.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String>  {
}
