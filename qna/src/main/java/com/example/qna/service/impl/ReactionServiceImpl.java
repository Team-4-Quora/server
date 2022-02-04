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
        Reaction dbReacton = null;
        if(reaction.getAnswerId()!=null){
            dbReacton = findByAnswerIdAndReactionBy(reaction.getAnswerId(),reaction.getReactionBy());
        } else {
            dbReacton= findByQuestionIdAndReactionBy(reaction.getQuestionId(),reaction.getReactionBy());
        }

        if(dbReacton==null){
            reactionRepository.save(reaction);
        }
        else if(dbReacton.getLike()==reaction.getLike()){
            delete(reaction.getId());
        }else if(dbReacton.getLike()!=reaction.getLike()) {
            dbReacton.setLike(!reaction.getLike());
            reactionRepository.save(dbReacton);
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