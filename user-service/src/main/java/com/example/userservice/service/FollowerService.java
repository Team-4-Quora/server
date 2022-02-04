package com.example.userservice.service;

import com.example.userservice.entity.Follower;

public interface FollowerService {
    Follower findByEmail(String email);

    Follower findByorgId(String orgid);
    Iterable<Follower> findall();


}


