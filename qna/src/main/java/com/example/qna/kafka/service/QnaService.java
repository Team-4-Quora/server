package com.example.qna.kafka.service;

import com.example.qna.kafka.dto.Qna;

public interface QnaService {
    void sendMessage(Qna qna);
}
