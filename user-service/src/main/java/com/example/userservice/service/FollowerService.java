package com.example.userservice.service;

import com.example.userservice.entity.Follower;

import java.util.List;

public interface FollowerService {
    List<Follower> findByEmail(String email);

    List<Follower> findByOrgId(String orgid);
    Iterable<Follower> findall();


}


