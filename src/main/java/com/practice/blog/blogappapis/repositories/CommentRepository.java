package com.practice.blog.blogappapis.repositories;

import com.practice.blog.blogappapis.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Integer> {
}
