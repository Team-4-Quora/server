package com.example.qna.kafka.service.impl;

import com.example.qna.kafka.dto.Qna;
import com.example.qna.kafka.service.QnaService;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class QnaServiceImpl implements QnaService {
    @Autowired
    private KafkaTemplate<String, Qna>
            kafkaTemplate;

    private static final String TOPIC
            = "postTopicAnalysis";
    @Override
    public void sendMessage(Qna qna) {
        qna.setReactionTime(new Timestamp(System.currentTimeMillis()));
        qna.setPostType("Quora");
        System.out.println(qna.getPostId());
        kafkaTemplate.send(
                TOPIC, qna
        );

        System.out.println("Published successfully");
    }
}
