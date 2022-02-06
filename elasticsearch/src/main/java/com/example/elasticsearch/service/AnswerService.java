package com.example.elasticsearch.service;

import com.example.elasticsearch.document.Answer;
import com.example.elasticsearch.document.Question;
import com.example.elasticsearch.repository.AnswerRepository;
import com.example.elasticsearch.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//todo : interface is missing
@Service
public class AnswerService {

    @Autowired
    AnswerRepository repository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public AnswerService(ElasticsearchRestTemplate elasticsearchRestTemplate)
    {
        this.elasticsearchRestTemplate=elasticsearchRestTemplate;
    }

    public void save(Answer answer) {
        repository.save(answer);
    }

    public List<Answer> getAll() {
        List<Answer> ansList = new ArrayList<>();
        Iterable<Answer> ans = repository.findAll();
        ans.forEach(ansList::add);
        return ansList;
    }


    public List<Answer> searchAns(String query) {
        return repository.searchAns(query);
    }

}
