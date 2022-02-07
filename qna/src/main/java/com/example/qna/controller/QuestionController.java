package com.example.qna.controller;

import com.example.qna.Request.Followers;
import com.example.qna.dto.QuestionDto;
import com.example.qna.entity.Question;
import com.example.qna.kafka.dto.Notification;
import com.example.qna.kafka.service.NotificationService;
import com.example.qna.service.QuestionService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/qna/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    NotificationService notificationService;

    @Autowired
    DirectExchange exchangeQnaElastic;

    @PostMapping("/add")
    void saveques(@RequestBody QuestionDto questionDto){
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        question.setPostedOn(Instant.now().getEpochSecond());
        questionService.save(question);
        rabbitTemplate.convertAndSend(exchangeQnaElastic.getName(),"routing.QnaElastic",question);
        Followers followers=restTemplate.exchange("http://10.177.1.70:8081/user/fetch/followers/"+question.getQuestionBy(),HttpMethod.POST,null,Followers.class).getBody();
        Notification notification=new Notification();
        notification.setTitle("New Post!");
        notification.setMessage("Your friend added a new post.");
        notification.setUserEmails(followers.getFollowers());
        notificationService.sendNotification(notification);
    }

    @GetMapping("/fetch/{type}/{value}")
    List<QuestionDto> fetchquesByValue(@PathVariable(value = "type") String type, @PathVariable(value="value") String value){
        List<Question> questions=questionService.findByValue(type,value);
        List<QuestionDto> result = new ArrayList<>();
        for(Question question: questions){
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            result.add(questionDto);
        }
        return result;
    }


}

