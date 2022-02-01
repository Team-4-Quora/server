package com.example.userservice.service;

import com.example.userservice.entity.Followers;

import java.util.List;

public interface FollowersService {
    Followers findByEmail(String email);
}
