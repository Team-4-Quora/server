package com.example.userservice.service.impl;

import com.example.userservice.Request.PointsRequest;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String email) {
        return userRepository.findById(email).get();
    }

    @Override
    public void incrementUser(PointsRequest pointsRequest) {
        User user= userRepository.findById(pointsRequest.getEmail()).get();
        if(pointsRequest.getInc())
            user.setPoints(user.getPoints()+ pointsRequest.getAmount());
        else
            user.setPoints(user.getPoints()- pointsRequest.getAmount());

        userRepository.save(user);
    }
}
