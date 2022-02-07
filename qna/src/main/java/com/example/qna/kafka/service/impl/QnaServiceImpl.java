package com.example.qna.kafka.service.impl;

import com.example.qna.kafka.dto.Qna;
import com.example.qna.kafka.service.QnaService;
import net.minidev.json.JSONObject;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class QnaServiceImpl implements QnaService {
    @Autowired
    private KafkaTemplate<String, JSONObject>
            kafkaTemplate;

    private static final String TOPIC
            = "postTopicAnalysis";
    @Override
    public void sendMessage(Qna qna) {
        qna.setReactionTime(new Timestamp(System.currentTimeMillis()));
        qna.setPostType("Quora");
        System.out.println(qna.getPostId());
        JSONObject object=new JSONObject();
        object.put("postId",qna.getPostId());
        object.put("postName",qna.getPostName());
        object.put("postType",qna.getPostType());
        object.put("reactionType",qna.getReactionType());
        object.put("contentType",qna.getContentType());
        object.put("reactionTime",qna.getReactionTime());
        object.put("userId",qna.getUserId());
        System.out.println(object.get("userId"));
        kafkaTemplate.send(
                TOPIC, object
        );

        System.out.println("Published successfully");
    }
}
