package com.practice.blog.blogappapis.controllers;

import com.practice.blog.blogappapis.payloads.ApiResponse;
import com.practice.blog.blogappapis.payloads.CategoryDTO;
import com.practice.blog.blogappapis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDTO> createCategory(@RequestBody @Validated CategoryDTO categoryDTO){
        //this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    private ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    private ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") int categoryId){
        return ResponseEntity.ok(this.categoryService.getCategoryById(categoryId));
    }

    @PutMapping("/{categoryId}")
    private ResponseEntity<CategoryDTO> updateCategory(@RequestBody @Validated CategoryDTO categoryDTO, @PathVariable ("categoryId") int categoryId){
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO,categoryId));
    }

    @DeleteMapping("/{categoryId}")
    private ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("categoryId") int categoryId){
        this.categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }
}
