package com.example.qna.service.impl;

import com.example.qna.entity.Answer;
import com.example.qna.entity.Reaction;
import com.example.qna.repository.AnswerRepository;
import com.example.qna.service.AnswerService;
import com.example.qna.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ReactionService reactionService;

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(String id) {
        answerRepository.deleteById(id);
        Reaction reaction=reactionService.findByAnswerId(id);
        reactionService.delete(reaction.getId());
    }

    @Override
    public List<Answer> findByQuestionId(String id) {
        return answerRepository.findByQuestionId(id);
    }

}
