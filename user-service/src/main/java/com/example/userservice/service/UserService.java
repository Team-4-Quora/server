package com.example.userservice.service;

import com.example.userservice.Request.PointsRequest;
import com.example.userservice.entity.User;

public interface UserService {
    User findById(String email);
    void incrementUser(PointsRequest pointsRequest);
}
