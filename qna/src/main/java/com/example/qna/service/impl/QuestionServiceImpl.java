package com.example.qna.service.impl;

import com.example.qna.entity.Question;
import com.example.qna.repository.QuestionRepository;
import com.example.qna.service.AnswerService;
import com.example.qna.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerService answerService;

    @Override
    public void save(Question question) {
     questionRepository.save(question);
    }

    @Override
    public List<Question> findByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    @Override
    public List<Question> findByQuestionBy(String email) {
        return questionRepository.findByQuestionBy(email);
    }

}
