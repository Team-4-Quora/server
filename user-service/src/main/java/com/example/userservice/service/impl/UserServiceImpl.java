package com.example.userservice.service.impl;

import com.example.userservice.Request.PointRequest;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        List<User> users=userRepository.findByEmail(email);
        User user=null;
        if (users.size()!=0){
            user=users.get(0);
        }
        return user;
    }

    @Override
    public void incrementUser(PointRequest pointRequest) {
        List<User> users= userRepository.findByEmail(pointRequest.getEmail());
        User user=null;
        if (users.size()!=0){
            user=users.get(0);
        }
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
