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
    public Question findById(String id) {
        return questionRepository.findById(id).get();
    }


    @Override
    public List<Question> findByValue(String type, String value) {
        switch (type){
            case "category": return questionRepository.findByCategory(value);
            case "questionBy":        return questionRepository.findByQuestionBy(value);
            case "orgId":        return questionRepository.findByOrgId(value);
            default: return null;
        }
    }
}
