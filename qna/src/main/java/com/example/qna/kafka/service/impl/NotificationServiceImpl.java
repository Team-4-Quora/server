package com.example.qna.kafka.service.impl;
import com.example.qna.kafka.dto.Notification;
import com.example.qna.kafka.service.NotificationService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private KafkaTemplate<String, JSONObject>
            kafkaTemplate;
    private static final String TOPIC
            = "quoraNotiftoken";
    @Override
    public void sendNotification(Notification notification) {
        JSONObject object=new JSONObject();
        object.put("title",notification.getTitle());
        object.put("message",notification.getMessage());
        object.put("appId",notification.getAppId());
        object.put("userEmails",notification.getUserEmails());
        kafkaTemplate.send(
                TOPIC, object
        );
        System.out.println("Published successfully");
    }
}