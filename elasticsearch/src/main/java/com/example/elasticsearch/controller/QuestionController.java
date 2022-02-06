package com.example.elasticsearch.controller;


import com.example.elasticsearch.document.Question;
import com.example.elasticsearch.document.User;
import com.example.elasticsearch.dto.QuestionDto;
import com.example.elasticsearch.dto.UserDto;
import com.example.elasticsearch.service.QuestionService;
import com.example.elasticsearch.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@EnableRabbit
@RestController
@RequestMapping("/ques")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RabbitListener(queues = "queue.QnaElastic")
    @PostMapping("/save")
    public void save(@RequestBody QuestionDto questionDto) {
        System.out.println(questionDto.getText()+"QuesHere");
        Question question = new Question();
        BeanUtils.copyProperties(questionDto,question);
        questionService.save(question);
    }

    @GetMapping("/all")
    public List<Question> getAll() {
        return questionService.getAll();
    }


    @GetMapping("/searchques/{query}")
    public List<QuestionDto> searchUser(@PathVariable("query") String query) {
        try {
            List<Question> questions = questionService.searchQues(query);

            List<QuestionDto> questionDtos = new ArrayList<>();

            for(Question question:questions){
                QuestionDto questionDto = new QuestionDto();
                BeanUtils.copyProperties(question,questionDto);
                questionDtos.add(questionDto);
            }
            return questionDtos;

//            JSONObject data = new JSONObject();
//            data.put("question", questions);
//            return  prepareReturnObject(200, "Search data", data);
//        } catch (Exception e) {
//            return prepareReturnObject(500, "Some error occurred", null);
//        }
        }catch (Exception e)
        {
            return null;
        }

    }


    public JSONObject prepareReturnObject(int status, String message, JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", status);
        jsonObject.put("message", message);
        jsonObject.put("data", data);
        return jsonObject;
    }
}
