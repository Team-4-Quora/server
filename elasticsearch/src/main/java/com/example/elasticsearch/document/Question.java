package com.example.elasticsearch.document;

import com.example.elasticsearch.helper.Indices;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = Indices.Ques_index)

public class Question {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String orgId;

    @Field(type = FieldType.Text)
    private String questionBy;

    @Field(type = FieldType.Long)
    private Long postedOn;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Text)
    private String shareableLink;

    @Field(type = FieldType.Boolean)
    private Boolean isThreadClosed=false;

    @Field(type = FieldType.Text)
    private String category;

    @Field(type = FieldType.Text)
    private String acceptedAnswer;

    @Field(type = FieldType.Boolean)
    private Boolean isModerated=false;


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
