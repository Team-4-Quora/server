package com.example.userservice.service.impl;

import com.example.userservice.Request.PointRequest;
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
        return userRepository.findByEmail(email);
    }

    // todo : this should be consumed from Kafka / RabbitMQ
    @Override
    public void incrementUser(PointRequest pointRequest) {
        User user= userRepository.findByEmail(pointRequest.getEmail());
        if(pointRequest.getInc())
            user.setPoints(user.getPoints()+ pointRequest.getAmount());
        else
            user.setPoints(user.getPoints()- pointRequest.getAmount());
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
