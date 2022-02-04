package com.example.qna.service.impl;

import com.example.qna.entity.Answer;
import com.example.qna.entity.Question;
import com.example.qna.entity.Reaction;
import com.example.qna.repository.AnswerRepository;
import com.example.qna.repository.QuestionRepository;
import com.example.qna.service.AnswerService;
import com.example.qna.service.CommentService;
import com.example.qna.service.QuestionService;
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
    @Autowired
    private CommentService commentService;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void delete(String id) {
        Answer answer=findById(id);
        Reaction reaction=reactionService.findByAnswerIdAndReactionBy(id,answer.getAnswerBy());
        reactionService.delete(reaction.getId());
        commentService.deleteByAnswerId(answer.getId());
        answerRepository.delete(answer);
    }

    @Override
    public Answer findById(String id) {
        return answerRepository.findById(id).get();
    }

    @Override
    public List<Answer> findByQuestionId(String id) {
        return answerRepository.findByQuestionId(id);
    }

    @Override
    public void setAnswerAccepted(String id) {
        Answer answer=findById(id);
        answer.setAccepted(true);
        save(answer);
    }

}
