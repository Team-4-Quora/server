package com.example.userservice.service;

import com.example.userservice.dto.FollowerDto;
import com.example.userservice.entity.Follower;

import java.util.List;

public interface FollowerService {
    List<Follower> findByEmail(String email);

    List<Follower> findByOrgId(String orgid);
    Iterable<Follower> findall();
    void save(Follower follower);
    void acceptRequest(String id,String reqId);
    List<FollowerDto> fetchFollowerData(String type, String id);

}


