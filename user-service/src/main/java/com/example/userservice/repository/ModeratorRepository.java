package com.example.userservice.repository;

import com.example.userservice.entity.Moderator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends MongoRepository<Moderator,String> {
}
