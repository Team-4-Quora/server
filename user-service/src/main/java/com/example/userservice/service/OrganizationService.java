package com.example.userservice.service;

import com.example.userservice.entity.Organization;

import java.util.List;

public interface OrganizationService {
    void save(Organization organization);
    Organization findById(String id);
    List<Organization> findByEmail(String email);
}
