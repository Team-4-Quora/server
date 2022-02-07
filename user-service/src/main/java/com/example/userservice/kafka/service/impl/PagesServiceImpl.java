package com.example.userservice.kafka.service.impl;

import com.example.userservice.kafka.dto.Pages;
import com.example.userservice.kafka.service.PagesService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class PagesServiceImpl implements PagesService {

    @Autowired
    private KafkaTemplate<String, JSONObject>
            kafkaTemplate;

    private static final String TOPIC
            = "pageTopicAnalytics";

    @Override
    public void sendOrgData(Pages page) {
        page.setUserViewTime(new Timestamp(System.currentTimeMillis()));
        JSONObject object=new JSONObject();
        object.put("pageId",page.getPageId());
        object.put("userId",page.getUserId());
        object.put("userViewTime",page.getUserViewTime());
        object.put("pageName",page.getPageName());
        kafkaTemplate.send(
                TOPIC, object
        );

        System.out.println("Published successfully");
    }
}
