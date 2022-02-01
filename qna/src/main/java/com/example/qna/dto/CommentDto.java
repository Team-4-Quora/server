package com.example.qna.dto;

import java.sql.Timestamp;

public class CommentDto {
    private String id;
    private String answerId;
    private String message;
    private Timestamp postedOn;
    private String commentBy;

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Timestamp postedOn) {
        this.postedOn = postedOn;
    }
}
