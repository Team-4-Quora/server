package com.example.userservice.controller;

import com.example.userservice.Request.PointRequest;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DirectExchange exchangeUserElastic;

    @Autowired
    private UserService userService;

    @GetMapping("/stats/{email}")
    private UserDto getUserStats(@PathVariable(value = "email") String email){
        User user=userService.findById(email);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @PostMapping("/add")
    private void saveuser(User user){
        userService.saveuser(user);
        rabbitTemplate.convertAndSend(exchangeUserElastic.getName(),"routing.UserElastic",user);
    }

    @PostMapping("/points")
    private void incUserPoints(@RequestBody PointRequest pointRequest){
        userService.incrementUser(pointRequest);
    }
}
