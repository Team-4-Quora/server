package com.example.userservice.controller;

import com.example.userservice.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class FollowersController {

    @Autowired
    private FollowersService followersService;

    @GetMapping("/fetch/{type}/{email}")
    List<String> getUsersList(@PathVariable(name = "type") String type,@PathVariable(name = "email") String email){
        switch (type){
            case "followers":
                return followersService.findByEmail(email).getFollowers();
            case "following":
                return followersService.findByEmail(email).getFollowing();
            case "pending":
                return followersService.findByEmail(email).getPending();
            default:
                return followersService.findByEmail(email).getRejected();
        }




    }
}
