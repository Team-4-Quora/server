package com.example.qna.controller;

import com.example.qna.dto.QuestionDto;
import com.example.qna.entity.Question;
import com.example.qna.service.QuestionService;
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

    @PostMapping("/add")
    void saveques(@RequestBody QuestionDto questionDto){
        Question question=new Question();
        BeanUtils.copyProperties(questionDto,question);
        System.out.println("hiii");
        question.setPostedOn(Instant.now().getEpochSecond());
        questionService.save(question);
    }

    @GetMapping("/fetch/{type}/{value}")
    List<QuestionDto> fetchquesByValue(@PathVariable(value = "type") String type, @PathVariable(value="value") String value){
        List<Question> questions=questionService.findByValue(type,value);
        List<QuestionDto> result = new ArrayList<>();
        System.out.println("I am here");
        for(Question question: questions){

            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            result.add(questionDto);
        }
        return result;
    }

    @PostMapping("/accepted/{id}/{ansId}")
    void setAcceptedAnswer(@PathVariable(value = "id") String id, @PathVariable(value = "ansId")String ansId){
        questionService.setAcceptedAnswer(id,ansId);
    }
}

