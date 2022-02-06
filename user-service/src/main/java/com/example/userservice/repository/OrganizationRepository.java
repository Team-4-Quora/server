package com.example.userservice.repository;

import com.example.userservice.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization,String> {
    List<Organization> findByOwner(String email);

}
