package com.example.userservice.service;

import com.example.userservice.entity.Organization;

public interface OrganizationService {
    void save(Organization organization);
    Organization findById(String id);
}
