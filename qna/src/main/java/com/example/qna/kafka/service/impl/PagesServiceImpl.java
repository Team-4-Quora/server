package com.example.qna.kafka.service.impl;

import com.example.qna.kafka.dto.Pages;
import com.example.qna.kafka.dto.Qna;
import com.example.qna.kafka.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

import java.sql.Timestamp;

@EnableKafka
public class PagesServiceImpl implements PagesService {

    @Autowired
    KafkaTemplate<String, Pages>
            kafkaTemplate;

    private static final String TOPIC
            = "pageTopicAnalytics";

    @Override
    public void sendOrgData(Pages page) {
        page.setUserViewTime(new Timestamp(System.currentTimeMillis()));
        kafkaTemplate.send(
                TOPIC, page
        );

        System.out.println("Published successfully");
    }
}
