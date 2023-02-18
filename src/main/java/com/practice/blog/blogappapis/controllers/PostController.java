package com.practice.blog.blogappapis.controllers;


import com.practice.blog.blogappapis.config.AppConstants;
import com.practice.blog.blogappapis.payloads.ApiResponse;
import com.practice.blog.blogappapis.payloads.PostDTO;
import com.practice.blog.blogappapis.payloads.PostResponse;
import com.practice.blog.blogappapis.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostService postService;

    //Create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") int userId, @PathVariable("categoryId") int categoryId){
        return new ResponseEntity<>(this.postService.createPost(postDTO,userId,categoryId), HttpStatus.CREATED);
    }


    //Get allPosts

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        return ResponseEntity.ok(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir));
    }

    //Get post by Id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("postId") int postId){
        return  new ResponseEntity<>(this.postService.getPostById(postId),HttpStatus.OK);
    }
   //Get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser(@PathVariable("userId") int userId,@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNumber,
                                                       @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)int pageSize,
                                                       @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                       @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        return new ResponseEntity<>(this.postService.getPostByUser(userId,pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    //Get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory(@PathVariable("categoryId") Integer categoryId,
                                                           @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)int pageNumber,
                                                           @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)int pageSize,
                                                           @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
                                                           @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        return new ResponseEntity<>(this.postService.getPostByCategory(categoryId,pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable("postId") int postId){
       return new ResponseEntity<>(this.postService.updatePost(postDTO,postId),HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable("postId") int postId ){

        this.postService.deletePostById(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }

    //search by title
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable("keyword") String keyword){
        return new ResponseEntity<>(this.postService.searchPost(keyword),HttpStatus.OK);
    }

}
