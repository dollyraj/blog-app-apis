package com.practice.blog.blogappapis.payloads;

import com.practice.blog.blogappapis.entities.Post;

public class CommentDTO {

    private int commentId;
    private String content;



    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
