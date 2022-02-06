package com.example.recomandadds.controller;

import com.example.recomandadds.dto.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("analyze")
public class AnalysisController {

    @Autowired
    private KafkaTemplate<String, Question>
            kafkaTemplate;

    private static final String TOPIC
            = "StudentExample";

    @GetMapping("/publish/{id}/"
            + "{firstName}/{lastName}")

    public String post(
            @PathVariable("id") final int id,
            @PathVariable("firstName") final
            String firstName,
            @PathVariable("lastName") final
            String lastName)
    {

        kafkaTemplate.send(
                TOPIC,
                new Student(
                        id, firstName,
                        lastName));

        return "Published successfully";
    }
}
