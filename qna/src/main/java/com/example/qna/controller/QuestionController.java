package com.example.qna.controller;

import com.example.qna.dto.QuestionDto;
import com.example.qna.entity.Question;
import com.example.qna.service.QuestionService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qna/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DirectExchange exchangeQnaElastic;

    @PostMapping("/add")
    void saveques(@RequestBody QuestionDto questionDto){
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        question.setPostedOn(Instant.now().getEpochSecond());
        questionService.save(question);
        rabbitTemplate.convertAndSend(exchangeQnaElastic.getName(),"routing.QnaElastic",question);

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

