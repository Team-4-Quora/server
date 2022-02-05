package com.example.userservice.service.impl;

import com.example.userservice.dto.FollowerDto;
import com.example.userservice.entity.Follower;
import com.example.userservice.repository.FollowerRepository;
import com.example.userservice.service.FollowerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    @Override
    public List<Follower> findByEmail(String email) {
        return followerRepository.findByEmail(email);
    }

    @Override
    public List<Follower> findByOrgId(String orgid){
        return followerRepository.findByOrgId(orgid);

    }

    @Override
    public Iterable<Follower> findall() {
        return followerRepository.findAll();
    }

    @Override
    public void save(Follower follower) {
        followerRepository.save(follower);
    }

    @Override
    public void acceptRequest(String id, String reqId) {
        List<Follower> followList=followerRepository.findByEmailAndRequesterId(id,reqId);
        if(followList.size()>0){
            Follower follower=followList.get(0);
            follower.setStatus(1);
            followerRepository.save(follower);
        }

    }

    @Override
    public List<FollowerDto> fetchFollowerData(String type,String id) {
        List<Follower> followers=null;
        switch (type){
            case "followers": {
                followers=followerRepository.findByEmailAndStatus(id,1);
                break;
            }
            case "following":{
                followers=followerRepository.findByRequesterIdAndStatus(id,1);
                break;
            }case "pending":{
                followers = followerRepository.findByEmailAndStatus(id,0);
                break;
            }default:break;
        }
        List<FollowerDto> followerDto=new ArrayList<>();
        for(Follower follower:followers){
            FollowerDto followerDto1=new FollowerDto();
            BeanUtils.copyProperties(follower, followerDto1);
            followerDto.add(followerDto1);
        }
        return followerDto;
    }
}
