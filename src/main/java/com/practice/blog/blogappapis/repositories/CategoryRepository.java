package com.practice.blog.blogappapis.repositories;

import com.practice.blog.blogappapis.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
}
