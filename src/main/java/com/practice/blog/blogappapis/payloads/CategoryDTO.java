package com.practice.blog.blogappapis.payloads;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDTO {

    private int categoryId;

    @NotEmpty
    @Size(min=4,message="Category title must be of minimum 4 characters!")
    private String categoryTitle;
    @NotEmpty(message = "Category description cannot be empty!")
    //@Size(min=4,message="Category title must be of minimum 4 characters!")
    private String categoryDescription;

    public CategoryDTO(){

    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
