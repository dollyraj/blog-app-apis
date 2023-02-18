package com.practice.blog.blogappapis.payloads;

import com.practice.blog.blogappapis.entities.Category;
import com.practice.blog.blogappapis.entities.Comment;
import com.practice.blog.blogappapis.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDTO {

    private int id;
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private Category category;
    private User user;

    private Set<CommentDTO>  comments=new HashSet<>();

    public Set<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDTO> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
