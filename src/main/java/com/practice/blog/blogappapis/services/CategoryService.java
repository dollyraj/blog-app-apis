package com.practice.blog.blogappapis.services;

import com.practice.blog.blogappapis.entities.Category;
import com.practice.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.practice.blog.blogappapis.payloads.CategoryDTO;
import com.practice.blog.blogappapis.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category=this.DtoToCategory(categoryDTO);
        Category savedCategory=this.categoryRepository.save(category);

        return this.categoryToDto(savedCategory);
    }

    public CategoryDTO getCategoryById(int categoryId){
        if(!this.categoryRepository.findById(categoryId).isPresent()){
            throw new ResourceNotFoundException("Category","Id",categoryId);
        }
        Category category=this.categoryRepository.findById(categoryId).get();
        return this.categoryToDto(category);
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categoryList= (List<Category>) this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList=new ArrayList<>();

        for(Category category:categoryList){
            categoryDTOList.add(this.categoryToDto(category));
        }

        return categoryDTOList;

    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO,int categoryId){

        if(!this.categoryRepository.findById(categoryId).isPresent()){
            throw new ResourceNotFoundException("Category","Id",categoryId);
        }

        Category category=this.categoryRepository.findById(categoryId).get();
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory=this.categoryRepository.save(category);

        return this.categoryToDto(updatedCategory);
    }

    public void deleteCategoryById(int categoryId){
        if(!this.categoryRepository.findById(categoryId).isPresent()){
            throw new ResourceNotFoundException("Category","Id",categoryId);
        }

        this.categoryRepository.deleteById(categoryId);
    }

    private CategoryDTO categoryToDto(Category category){
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryTitle(category.getCategoryTitle());
        categoryDTO.setCategoryDescription(category.getCategoryDescription());

        return categoryDTO;
    }


    private Category DtoToCategory(CategoryDTO categoryDTO){
        Category category=new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        return category;
    }
}
