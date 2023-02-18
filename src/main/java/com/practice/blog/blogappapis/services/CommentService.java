package com.practice.blog.blogappapis.services;


import com.practice.blog.blogappapis.entities.Comment;
import com.practice.blog.blogappapis.entities.Post;
import com.practice.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.practice.blog.blogappapis.payloads.CommentDTO;
import com.practice.blog.blogappapis.repositories.CommentRepository;
import com.practice.blog.blogappapis.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO createComment(CommentDTO commentDTO,int postId){
        if(!this.postRepository.findById(postId).isPresent()){
            throw new ResourceNotFoundException("Post","Id",postId);
        }
        Post post=this.postRepository.findById(postId).get();
        Comment comment=this.dtoToComment(commentDTO);
        comment.setPost(post);
        Comment savedComment=this.commentRepository.save(comment);
        return this.commentToDto(savedComment);
    }

    public Set<CommentDTO> getAllCommentsOfAPost(int postId){
        if(!this.postRepository.findById(postId).isPresent()){
            throw new ResourceNotFoundException("Post","Id",postId);
        }

        List<Comment> commentList= (List<Comment>) this.commentRepository.findAll();

        Set<CommentDTO> commentDTOSet=new HashSet<>();
        for(Comment c:commentList){
            commentDTOSet.add(this.commentToDto(c));
        }

        return commentDTOSet;

    }

    public void deleteComment(int commentId){
          if(!this.commentRepository.findById(commentId).isPresent()){
              throw new ResourceNotFoundException("Comment","Id",commentId);
          }

          this.commentRepository.deleteById(commentId);
    }

    private Comment dtoToComment(CommentDTO commentDTO){
        Comment comment=new Comment();
        comment.setContent(commentDTO.getContent());
        return comment;
    }

    public CommentDTO commentToDto(Comment comment){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setContent(comment.getContent());

        return commentDTO;
    }
}
