package com.example.qna.service;

import com.example.qna.entity.Question;

import java.util.List;

public interface QuestionService {
    void save(Question question);
    List<Question> findByCategory(String category);
    List<Question> findByQuestionBy(String email);

}
