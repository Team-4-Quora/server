package com.example.qna.service.impl;

import com.example.qna.entity.Comment;
import com.example.qna.repository.CommentRepository;
import com.example.qna.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByAnswerId(String id) {
        return commentRepository.findByAnswerId(id);
    }

    @Override
    public void deleteByAnswerId(String id) {
        commentRepository.deleteByAnswerId(id);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
