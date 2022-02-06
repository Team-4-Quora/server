package com.example.recomandadds.dto;

import java.sql.Timestamp;

public class Qna {
    private Long id;
    private String postId;
    private String postName;
    private String contentType;
    private String reactionType;
    private Timestamp reactionTime;
    private String userId;

    public Timestamp getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Timestamp reactionTime) {
        this.reactionTime = reactionTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
