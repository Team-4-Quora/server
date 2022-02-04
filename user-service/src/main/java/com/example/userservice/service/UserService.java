package com.example.userservice.service;

import com.example.userservice.Request.PointRequest;
import com.example.userservice.entity.User;

public interface UserService {
    User findById(String email);
    void incrementUser(PointRequest pointRequest);
    void saveuser(User user);
}
