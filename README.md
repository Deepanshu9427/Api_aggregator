This project is a solution to an assignment. The project displays how to use external API in your project. It is developed using SpringBoot framework.
# API Data Aggregator

This is a Spring Boot application that fetches data from a public API (JSONPlaceholder), caches it in an in-memory database, and exposes clean REST endpoints with filtering capabilities.

## Features
* **Data Ingestion**: Fetches Users and Posts from external APIs on startup.
* **Caching**: Stores data in H2 in-memory database for fast retrieval.
* **Filtering**: Allows filtering posts by User ID.
* **Error Handling**: specific handling for network timeouts and missing resources.

## Tech Stack
* Java 17
* Spring Boot 3.x
* Spring Data JPA & H2 Database
* Spring RestClient

## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Deepanshu9427/Api_aggregator.git
    cd api
    ```

2.  **Build and Run:**
    ```bash
    ./mvnw spring-boot:run
    ```

3.  **Access the Application:**
    The server will start on `http://localhost:8080`.

## API Endpoints

### 1. List All Posts
Returns a list of all ingested posts.
* **URL:** `GET /api/v1/posts`
* **Response:** JSON Array of posts.

### 2. Filter Posts by User
Returns posts belonging to a specific user.
* **URL:** `GET /api/v1/posts?userId=1`
* **Query Param:** `userId` (Integer) - The ID of the user.

### 3. Get Post by ID
Returns details of a specific post.
* **URL:** `GET /api/v1/posts/{id}`
* **Example:** `GET /api/v1/posts/1`

## Implementation Notes & Assumptions
* **External API**: I used JSONPlaceholder. Since it is a mock API, I assumed the data structure is static.
* **Persistence**: I chose H2 (In-memory) because the requirements asked for a "simple database" and it requires no installation for the reviewer. Data resets on application restart.
* **Timeouts**: The external API client is configured with a 5-second timeout to handle network lag.
