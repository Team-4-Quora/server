package com.example.elasticsearch.document;

import com.example.elasticsearch.helper.Indices;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = Indices.Ans_index)
public class Answer {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String questionId;

    @Field(type = FieldType.Text)
    private String answerBy;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Text)
    private Boolean isAccepted=false;

    @Field(type = FieldType.Text)
    private Long postedOn;

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

    public String getAnswerBy() {
        return answerBy;
    }

    public void setAnswerBy(String answerBy) {
        this.answerBy = answerBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Long getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Long postedOn) {
        this.postedOn = postedOn;
    }
}
