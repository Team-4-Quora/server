package com.example.elasticsearch.service;

import com.example.elasticsearch.document.User;
import com.example.elasticsearch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//todo : interface is missing
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public UserService(ElasticsearchRestTemplate elasticsearchRestTemplate)
    {
        this.elasticsearchRestTemplate=elasticsearchRestTemplate;
    }

    public void save(User user) {
        repository.save(user);
    }

    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        Iterable<User> user = repository.findAll();
        user.forEach(userList::add);
        return userList;
    }


    public List<User> searchUser(String query) {
        return repository.searchUser(query);
    }

}
