package com.example.qna.repository;

import com.example.qna.entity.Reaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends MongoRepository<Reaction,String> {
    List<Reaction> findByQuestionId(String id);
    List<Reaction> findByAnswerId(String id);
    Reaction findByQuestionIdAndReactionBy(String id,String email);
    Reaction findByAnswerIdAndReactionBy(String id,String email);
    void deleteByAnswerId(String id);

}
