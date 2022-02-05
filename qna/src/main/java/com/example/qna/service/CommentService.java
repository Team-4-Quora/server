package com.example.qna.service;

import com.example.qna.entity.Comment;

import java.util.List;

public interface CommentService {
    void save(Comment comment);
    List<Comment> findByAnswerIdAndParentComment(String id,String parentComment);
    List<Comment> findByAnswerId(String id);

    void deleteByAnswerId(String id);
    void deleteById(String id);
}
