package com.example.userservice.service.impl;

import com.example.userservice.entity.Organization;
import com.example.userservice.repository.OrganizationRepository;
import com.example.userservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void save(Organization organization) {
        organizationRepository.save(organization);
    }

    @Override
    public Organization findById(String id) {
        return organizationRepository.findById(id).get();
    }

    @Override
    public List<Organization> findByEmail(String email) {
        return organizationRepository.findByOwner(email);
    }
}
