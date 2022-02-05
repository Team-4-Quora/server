package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.entity.Follower;
import com.example.userservice.service.FollowerService;
import com.example.userservice.service.UserService;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private UserService userService;

    @GetMapping("/fetch/{email}")
    List<UserDto> getUsersList(@PathVariable(value = "email") String email){
        List<UserDto>  userFollowers=new ArrayList<>();
        List<Follower> temp= followerService.findByEmail(email);
        for(Follower follower :temp)
        {
            if(follower.getStatus()==1)
            {
                User user=userService.findById(follower.getRequesterEmail());
                UserDto userDto=new UserDto();
                BeanUtils.copyProperties(user,userDto);
                userFollowers.add(userDto);
            }
        }
        return userFollowers;
    }
}
