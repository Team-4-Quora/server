package com.example.qna.repository;

import com.example.qna.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends MongoRepository<Answer,String> {
    List<Answer> findByQuestionId(String id);

}
