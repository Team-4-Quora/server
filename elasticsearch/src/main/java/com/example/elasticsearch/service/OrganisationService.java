package com.example.elasticsearch.service;

import com.example.elasticsearch.document.Organisation;
import com.example.elasticsearch.document.User;
import com.example.elasticsearch.repository.OrganisationRepository;
import com.example.elasticsearch.repository.UserRepository;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {

    @Autowired
    OrganisationRepository repository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public OrganisationService(ElasticsearchRestTemplate elasticsearchRestTemplate)
    {
        this.elasticsearchRestTemplate=elasticsearchRestTemplate;
    }

    public void save(Organisation organisation) {
        repository.save(organisation);
    }

    public List<Organisation> getAll() {
        List<Organisation> orgList = new ArrayList<>();
        Iterable<Organisation> org = repository.findAll();
        org.forEach(orgList::add);
        return orgList;
    }


    public List<Organisation> searchOrg(String query) {
        return repository.searchInOrg(query);
    }

}
