package com.example.userservice.controller;

import com.example.userservice.Request.LoginRequest;
import com.example.userservice.Request.PointRequest;
import com.example.userservice.Request.UserRequest;
import com.example.userservice.dto.FollowerDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.Follower;
import com.example.userservice.entity.User;
import com.example.userservice.response.LoginResponse;
import com.example.userservice.service.FollowerService;
import com.example.userservice.service.UserService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UserController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DirectExchange exchangeUserElastic;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowerService followerService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/stats/{email}")
    private UserDto getUserStats(@PathVariable(value = "email") String email){
        User user=userService.findById(email);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @PostMapping("/register")
    private void registerUser(@RequestBody UserRequest userRequest){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<UserRequest> entity = new HttpEntity<UserRequest>(userRequest,headers);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        restTemplate.exchange("http://10.177.1.200:8000/authentication/authenticate/register",HttpMethod.POST,entity,UserRequest.class);
    }

    @PostMapping("/login")
    private void loginUser(@RequestBody LoginRequest userRequest){

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<LoginRequest> entity = new HttpEntity<LoginRequest>(userRequest,headers);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        LoginResponse loginResponse=restTemplate.exchange("http://10.177.1.200:8000/authentication/authenticate/login",HttpMethod.POST,entity,LoginResponse.class).getBody();

    }

    @PostMapping("/add")
    private void saveuser(@RequestBody UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        userService.save(user);
        rabbitTemplate.convertAndSend(exchangeUserElastic.getName(),"routing.UserElastic",user);
    }

    @PostMapping("/points")
    private void incUserPoints(@RequestBody PointRequest pointRequest){
        userService.incrementUser(pointRequest);
    }


}
