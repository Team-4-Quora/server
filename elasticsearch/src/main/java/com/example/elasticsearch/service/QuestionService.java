package com.example.elasticsearch.service;


import com.example.elasticsearch.document.Organisation;
import com.example.elasticsearch.document.Question;
import com.example.elasticsearch.repository.OrganisationRepository;
import com.example.elasticsearch.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//todo : interface is missing
@Service
public class QuestionService {

    @Autowired
    QuestionRepository repository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public QuestionService(ElasticsearchRestTemplate elasticsearchRestTemplate)
    {
        this.elasticsearchRestTemplate=elasticsearchRestTemplate;
    }

    public void save(Question question) {
        repository.save(question);
    }

    public List<Question> getAll() {
        List<Question> quesList = new ArrayList<>();
        Iterable<Question> ques = repository.findAll();
        ques.forEach(quesList::add);
        return quesList;
    }


    public List<Question> searchQues(String query) {
        return repository.searchQues(query);
    }

}
