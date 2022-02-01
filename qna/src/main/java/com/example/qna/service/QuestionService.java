package com.example.qna.service;

import com.example.qna.entity.Question;

import java.util.List;

public interface QuestionService {
    void save(Question question);
    List<Question> findByValue(String type,String value);
    void setAcceptedAnswer(String id,String answerId);
    Question findById(String id);

}
