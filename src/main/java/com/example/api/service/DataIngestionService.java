package com.example.api.service;


import com.example.api.model.AppUser;
import com.example.api.model.Post;
import com.example.api.repository.PostRepository;
import com.example.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.time.Duration;
import java.util.Arrays;

@Service
public class DataIngestionService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RestClient restClient;

    public DataIngestionService(UserRepository userRepository,
                                PostRepository postRepository,
                                @Value("${external.api.url}") String baseUrl,
                                @Value("${external.api.timeout}") int timeout) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;

        // Configure Timeout
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(timeout);
        factory.setReadTimeout(timeout);

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .requestFactory(factory)
                .build();
    }

    // This runs automatically when the app starts
    @Override
    public void run(String... args) {
        try {
            System.out.println("Fetching data from external API...");

            // Fetch Endpoint 1: Users
            AppUser[] users = restClient.get()
                    .uri("/users")
                    .retrieve()
                    .body(AppUser[].class);

            // Fetch Endpoint 2: Posts
            Post[] posts = restClient.get()
                    .uri("/posts")
                    .retrieve()
                    .body(Post[].class);

            if (users != null) userRepository.saveAll(Arrays.asList(users));
            if (posts != null) postRepository.saveAll(Arrays.asList(posts));

            System.out.println("Data successfully cached in H2 database.");

        } catch (Exception e) {
            // Logs error but keeps app running (Resilience)
            System.err.println("Failed to fetch initial data: " + e.getMessage());
        }
    }
}