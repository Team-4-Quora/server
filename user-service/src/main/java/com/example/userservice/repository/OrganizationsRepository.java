package com.example.userservice.repository;

import com.example.userservice.entity.Organizations;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationsRepository extends MongoRepository<Organizations,String> {
}
