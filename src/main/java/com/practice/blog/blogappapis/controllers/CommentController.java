package com.practice.blog.blogappapis.controllers;

import com.practice.blog.blogappapis.payloads.ApiResponse;
import com.practice.blog.blogappapis.payloads.CommentDTO;
import com.practice.blog.blogappapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable("postId") int postId){
        return new ResponseEntity<>(this.commentService.createComment(commentDTO,postId), HttpStatus.CREATED);
    }

    @GetMapping("/post/{postId}/comments")
    public ResponseEntity<Set<CommentDTO>> getAllCommentsOfAPost(@PathVariable("postId") int postId){
        return  new ResponseEntity<>(this.commentService.getAllCommentsOfAPost(postId),HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") int commentId){
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }
}
