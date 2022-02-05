package com.example.userservice.controller;

import com.example.userservice.entity.Follower;
import com.example.userservice.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @GetMapping("/fetch/{email}")
    List<String> getUsersList(@PathVariable(name = "email") String email){
        List<String>  userFollowers=new ArrayList<>();
        List<Follower> temp= followerService.findByEmail(email);
        for(Follower follower :temp)
        {
            if(follower.getStatus()==1)
            {
                userFollowers.add(follower.getRequesterId());
            }
        }
        return userFollowers;
    }
}
