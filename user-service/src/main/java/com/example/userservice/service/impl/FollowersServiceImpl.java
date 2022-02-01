package com.example.userservice.service.impl;

import com.example.userservice.entity.Followers;
import com.example.userservice.repository.FollowersRepository;
import com.example.userservice.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    FollowersRepository followersRepository;

    @Override
    public Followers findByEmail(String email) {
        return followersRepository.findByEmail(email);
    }
}
