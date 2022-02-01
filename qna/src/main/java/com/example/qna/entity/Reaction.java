package com.example.qna.entity;

import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reaction {
    @Id
    private String id;
    private String questionId;
    @NotNull
    private String reactionBy;
    @NotNull
    private Boolean isLike;
    private String answerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getReactionBy() {
        return reactionBy;
    }

    public void setReactionBy(String reactionBy) {
        this.reactionBy = reactionBy;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
