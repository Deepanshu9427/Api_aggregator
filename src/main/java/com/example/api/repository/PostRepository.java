package com.example.api.repository;

import com.example.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom filter method
    List<Post> findByUserId(Long userId);
}
