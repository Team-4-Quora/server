package com.example.qna.service;

import com.example.qna.entity.Reaction;

import java.util.List;

public interface ReactionService {
    List<Reaction> findByQuestionId(String id);
    List<Reaction> findByAnswerId(String id);
    Reaction findByQuestionIdAndReactionBy(String id,String email);
    Reaction findByAnswerIdAndReactionBy(String id,String email);
    void delete(String id);
    void save(Reaction reaction);
}
