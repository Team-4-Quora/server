package com.example.qna.controller;

import com.example.qna.dto.AnswerDto;
import com.example.qna.entity.Answer;
import com.example.qna.service.AnswerService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/qna/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchangeQnaElasticTwo;

    @PostMapping("/add")
    void saveans(@RequestBody AnswerDto answerDto){
        Answer answer=new Answer();
        BeanUtils.copyProperties(answerDto,answer);
        answer.setPostedOn(Instant.now().getEpochSecond());
        answerService.save(answer);
        rabbitTemplate.convertAndSend(exchangeQnaElasticTwo.getName(),"routing.QnaElasticTwo",answer);
    }

    @PostMapping("/delete/{id}")
    void delete(@PathVariable(value = "id") String id){
        answerService.delete(id);
    }

    @GetMapping("/fetch/{id}")
    List<AnswerDto> fetchByQuestionId(@PathVariable(value = "id") String id){
        List<Answer> answers=answerService.findByQuestionId(id);
        List<AnswerDto> answerDtos=new ArrayList<>();
        for (Answer answer:answers){
            AnswerDto answerDto= new AnswerDto();
            BeanUtils.copyProperties(answer,answerDto);
            answerDtos.add(answerDto);
        }
        return answerDtos;
    }


    @PostMapping("/accepted/{ansId}")
    void setAcceptedAnswer(@PathVariable(value = "ansId")String ansId){
        answerService.setAnswerAccepted(ansId);
    }

    @GetMapping("/fetch/accepted/{queId}")
    AnswerDto getAcceptedAnswer(@PathVariable(value = "queId") String queId){
        List<Answer> answers=answerService.findByQuestionIdAndIsAccepted(queId);
        AnswerDto selectedAnswer=new AnswerDto();
        if (answers.size()>0){
        BeanUtils.copyProperties(answers.get(0),selectedAnswer);
        return selectedAnswer;
        }
        return null;
    }

}
