package com.example.qna.kafka.service;

import com.example.qna.kafka.dto.Notification;

public interface NotificationService {
    void sendNotification(Notification notification);
}
