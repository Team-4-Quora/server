package com.example.userservice.repository;

import com.example.userservice.entity.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends MongoRepository<Follower,String> {
    Follower findByEmail(String email);
    Follower findByorgId(String orgid);

}
