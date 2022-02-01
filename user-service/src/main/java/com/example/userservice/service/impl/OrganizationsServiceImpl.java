package com.example.userservice.service.impl;

import com.example.userservice.entity.Organizations;
import com.example.userservice.repository.OrganizationsRepository;
import com.example.userservice.service.OrganizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {
    @Autowired
    private OrganizationsRepository organizationsRepository;

    @Override
    public void save(Organizations organization) {
        organizationsRepository.save(organization);
    }

    @Override
    public Organizations findById(String id) {
        return organizationsRepository.findById(id).get();
    }
}
