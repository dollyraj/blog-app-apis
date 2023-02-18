package com.practice.blog.blogappapis.repositories;

import com.practice.blog.blogappapis.entities.Category;
import com.practice.blog.blogappapis.entities.Post;
import com.practice.blog.blogappapis.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Integer> {

    Page<Post> findByCategory(Category category,Pageable pageable);
    Page<Post> findByUser(User user,Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    List<Post> findByTitleContaining(String title);
}
