package com.example.qna.repository;

import com.example.qna.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question,String> {
    List<Question> findByCategory(String category);
    List<Question> findByQuestionBy(String email);
    List<Question> findByOrgId(String orgId);
}
