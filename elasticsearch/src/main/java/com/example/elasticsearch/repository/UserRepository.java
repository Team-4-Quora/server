package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User,String> {

    public List<User> findAllByName(String name);

    @Query("{ \"multi_match\": { \"fields\":  [\"name\",\"email\"], \"query\": \"?0\", \"fuzziness\": \"100\"}}}")
    public List<User> search(String query);


}
