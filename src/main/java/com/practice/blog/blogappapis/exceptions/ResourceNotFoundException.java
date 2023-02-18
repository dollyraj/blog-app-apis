package com.practice.blog.blogappapis.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName,String fieldName,int fieldValue) {
        super(String.format("%s not found with %s : %d",resourceName,fieldName,fieldValue));
    }
}
