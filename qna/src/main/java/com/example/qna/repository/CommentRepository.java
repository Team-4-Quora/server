package com.example.qna.repository;

import com.example.qna.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByAnswerIdAndParentComment(String id,String parentComment);
    List<Comment> findByAnswerId(String id);

    void deleteByAnswerId(String id);
}
