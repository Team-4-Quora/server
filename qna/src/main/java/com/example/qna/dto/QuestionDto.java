package com.example.qna.dto;

import java.sql.Timestamp;

public class QuestionDto {
    private String id;
    private String orgId;
    private String questionBy;
    private Long postedOn;
    private String text;
    private String shareableLink;
    private Boolean isThreadClosed;
    private String category;
    private String acceptedAnswer;
    private Boolean isModerated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getQuestionBy() {
        return questionBy;
    }

    public void setQuestionBy(String questionBy) {
        this.questionBy = questionBy;
    }

    public Long getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Long postedOn) {
        this.postedOn = postedOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getShareableLink() {
        return shareableLink;
    }

    public void setShareableLink(String shareableLink) {
        this.shareableLink = shareableLink;
    }

    public Boolean getThreadClosed() {
        return isThreadClosed;
    }

    public void setThreadClosed(Boolean threadClosed) {
        isThreadClosed = threadClosed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public void setAcceptedAnswer(String acceptedAnswer) {
        this.acceptedAnswer = acceptedAnswer;
    }

    public Boolean getModerated() {
        return isModerated;
    }

    public void setModerated(Boolean moderated) {
        isModerated = moderated;
    }
}
