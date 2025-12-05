package com.example.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    private Long id;
    private String name;
    private String email;
    private String website;
}
