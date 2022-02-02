package com.example.qna.service.impl;

import com.example.qna.entity.Reaction;
import com.example.qna.repository.ReactionRepository;
import com.example.qna.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    @Override
    public void save(Reaction reaction) {
        Reaction answer=findByAnswerIdAndReactionBy(reaction.getAnswerId(),reaction.getReactionBy());
        Reaction question =findByQuestionIdAndReactionBy(reaction.getQuestionId(),reaction.getReactionBy());
        if(answer==null || question==null){
            reactionRepository.save(reaction);
        }
        else if(answer.getLike()==reaction.getLike() || question.getLike()==reaction.getLike()){
            delete(reaction.getId());
        }else if(answer.getLike()!=reaction.getLike()){
            answer.setLike(!reaction.getLike());
            reactionRepository.save(answer);
        }else if(question.getLike()!=reaction.getLike()){
            question.setLike(!reaction.getLike());
            reactionRepository.save(question);
        }

    }

    @Override
    public List<Reaction> findByQuestionId(String id) {
        return reactionRepository.findByQuestionId(id);
    }

    @Override
    public List<Reaction> findByAnswerId(String id) {
        return reactionRepository.findByAnswerId(id);
    }

    @Override
    public void delete(String id) {
        reactionRepository.deleteById(id);
    }

    @Override
    public Reaction findByQuestionIdAndReactionBy(String id, String email) {
        return reactionRepository.findByQuestionIdAndReactionBy(id,email);
    }

    @Override
    public Reaction findByAnswerIdAndReactionBy(String id, String email) {
        return reactionRepository.findByAnswerIdAndReactionBy(id,email);
    }
}
