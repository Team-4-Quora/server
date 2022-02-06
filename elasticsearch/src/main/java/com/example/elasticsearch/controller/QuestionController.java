package com.example.elasticsearch.controller;


import com.example.elasticsearch.document.Question;
import com.example.elasticsearch.document.User;
import com.example.elasticsearch.dto.QuestionDto;
import com.example.elasticsearch.dto.UserDto;
import com.example.elasticsearch.service.QuestionService;
import com.example.elasticsearch.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ques")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

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


    @GetMapping("/searchuser/{query}")
    public JSONObject searchUser(@PathVariable("query") String query) {
        try {
            List<Question> questions = questionService.searchQues(query);

            //todo : instead of JSONObject .. use Question DTO

            JSONObject data = new JSONObject();
            data.put("question", questions);
            return  prepareReturnObject(200, "Search data", data);
        } catch (Exception e) {
            return prepareReturnObject(500, "Some error occurred", null);
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
