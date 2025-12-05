package com.example.api.controller;

import com.example.api.model.Post;
import com.example.api.repository.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Requirement: List items with filtering options
    @GetMapping
    public ResponseEntity<List<Post>> getPosts(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return ResponseEntity.ok(postRepository.findByUserId(userId));
        }
        return ResponseEntity.ok(postRepository.findAll());
    }

    // Requirement: Show detailed view for a single item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + id));
    }
}