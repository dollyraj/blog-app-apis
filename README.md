# blog-app-apis
This is a collection of apis containing apis related to blog app

#### 1.  Clone the application
`https://github.com/dollyraj/blog-app-apis.git`

#### 2.  Create MYSQL Database
    create database blogapp

#### 3.  Change mysql username and password as per your installation
* Open   `src/main/resources/application.properties` 
* change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

#### 4. Build and run the app using maven
```
mvn package 
java -jar target/spring-boot-rest-api-tutorial-0.0.1-SNAPSHOT.jar
```

Alternatively, you can run the app without packaging it using -

`mvn spring-boot:run`

The app will start running at http://localhost:8080.

## Explore Rest API
The app defines following CRUD APIs.

### User API

|Method|Url|Description|
|----|---|-----------|
|POST|/api/users/|Create User|
|GET|/api/users/|Get all Users|
|GET|/api/users/{userId}|Get User By id|
|PUT|/api/users/{userId}|Update user|
|DELETE|/api/users/{userId}|Delete User|

POST  /api/users/

GET  /api/users/

GET  /api/users/{userId}

PUT  /api/users/{userId}

DELETE  /api/users/{userId}

### Category API
|Method|Url|Description|
|----|---|-----------|
|POST|/api/categories/|Create Category|
|GET|/api/categories/|Get all categories|
|GET|/api/categories/{categoryId}|Get Category By id|
|PUT|/api/categories/{categoryId}|Update category|
|DELETE|/api/categories/{categoryId}|Delete category|

POST  /api/categories/

GET  /api/categories/

GET  /api/categories/{categoryId}

PUT  /api/categories/{categoryId}

DELETE  /api/categories/{categoryId}

### Post API

|Method|Url|Description|
|----|---|-----------|
|POST|/api//user/{userId}/category/{categoryId}/posts|Create Post|
|GET|/api/posts|Get all Posts|
|GET|/api//posts/{postId}|Get Post By id|
|GET|/api/user/{userId}/posts|Get Post By a user|
|GET|/api/category/{categoryId}/posts|Get Post By a category|
|GET|/api/posts/search/{keyword}|Search a post|
|PUT|/api//posts/{postId}|Update post|
|DELETE|/api//posts/{postId}|Delete post|

POST  /api//user/{userId}/category/{categoryId}/posts

GET  /api/posts

GET  /api//posts/{postId}

GET  /api/user/{userId}/posts

GET  /api/category/{categoryId}/posts

PUT  /api//posts/{postId}

DELETE  /api//posts/{postId}

GET  /api/posts/search/{keyword}

### Comment API
|Method|Url|Description|
|----|---|-----------|
|POST|/api/post/{postId}/comments|Add comment|
|GET|/api/post/{postId}/comments|Get comments of a post|
|DELETE|/api/comments/{commentId}|Delete a comment|

POST  /api/post/{postId}/comments

GET  /api/post/{postId}/comments

DELETE  /api/comments/{commentId}
