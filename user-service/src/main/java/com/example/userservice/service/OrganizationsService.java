package com.example.userservice.service;

import com.example.userservice.entity.Organizations;

public interface OrganizationsService {
    void save(Organizations organization);
    Organizations findById(String id);
}
