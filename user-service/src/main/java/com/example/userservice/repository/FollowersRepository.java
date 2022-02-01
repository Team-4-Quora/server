package com.example.userservice.repository;

import com.example.userservice.entity.Followers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowersRepository extends MongoRepository<Followers,String> {
    Followers findByEmail(String email);
}
