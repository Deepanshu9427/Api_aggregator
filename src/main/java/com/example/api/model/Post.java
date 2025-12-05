package com.example.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    private Long id;
    private Long userId; // Foreign key logic
    private String title;
    private String body;
}
