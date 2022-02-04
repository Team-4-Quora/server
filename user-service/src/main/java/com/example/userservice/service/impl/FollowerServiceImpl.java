package com.example.userservice.service.impl;

import com.example.userservice.entity.Follower;
import com.example.userservice.repository.FollowerRepository;
import com.example.userservice.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    FollowerRepository followerRepository;

    @Override
    public Follower findByEmail(String email) {
        return followerRepository.findByEmail(email);
    }

    @Override
    public Follower findByorgId(String orgid){
        return followerRepository.findByorgId(orgid);

    }

    @Override
    public Iterable<Follower> findall() {
        return followerRepository.findAll();
    }
}
