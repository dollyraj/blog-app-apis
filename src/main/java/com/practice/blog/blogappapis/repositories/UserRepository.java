package com.practice.blog.blogappapis.repositories;

import com.practice.blog.blogappapis.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User,Integer>{
}
