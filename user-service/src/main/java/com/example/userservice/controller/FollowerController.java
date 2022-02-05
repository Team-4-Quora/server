package com.example.userservice.controller;


import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.entity.Follower;
import com.example.userservice.service.FollowerService;
import com.example.userservice.service.UserService;
import com.example.userservice.dto.FollowerDto;
import com.example.userservice.entity.Follower;
import com.example.userservice.service.FollowerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/follower")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @Autowired
    private UserService userService;

    @GetMapping("/fetch/{email}")
    List<UserDto> getUsersList(@PathVariable(value = "email") String email) {
        List<UserDto> userFollowers = new ArrayList<>();
        List<Follower> temp = followerService.findByEmail(email);
        for (Follower follower : temp) {
            if (follower.getStatus() == 1) {
                User user = userService.findById(follower.getRequesterId());
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user, userDto);
                userFollowers.add(userDto);
            }
        }

        return  userFollowers;
    }
//    @GetMapping("/fetch/{email}")
//    List<String> getUsersList(@PathVariable(name = "email") String email){
//        List<String>  userFollowers=new ArrayList<>();
//        List<Follower> temp= followerService.findByEmail(email);
//        for(Follower follower :temp)
//        {
//            if(follower.getStatus()==1)
//            {
//                userFollowers.add(follower.getRequesterId());
//            }
//        }
//        return userFollowers;
//    }

    @PostMapping("/add")
    private void followUser(@RequestBody FollowerDto followerDto){
        Follower follower=new Follower();
        BeanUtils.copyProperties(followerDto,follower);
        followerService.save(follower);
    }

    @GetMapping("/fetch/org/followers/{id}")
    List<FollowerDto> getFollowers(@PathVariable(value="id") String orgId)
    {
        List<Follower> followers = followerService.findByOrgId(orgId);
        List<FollowerDto> followerDto =new ArrayList<>();
        for(Follower follower: followers){
            FollowerDto followerDto1=new FollowerDto();
            BeanUtils.copyProperties(follower, followerDto1);
            followerDto.add(followerDto1);
        }
        return followerDto;
    }

    @PostMapping("/accept/{id}/{requesterId}")
    private void acceptRequest(@PathVariable(value="id") String id, @PathVariable(value = "requesterId") String reqId){
        followerService.acceptRequest(id,reqId);
    }
    @GetMapping("/fetch/{type}/{id}")
    private List<FollowerDto> fetchFollowerData(@PathVariable(value = "type") String type,@PathVariable(value = "id") String id){
        return followerService.fetchFollowerData(type,id);
    }
}
