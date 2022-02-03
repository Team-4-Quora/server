package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.Answer;
import com.example.elasticsearch.document.Organisation;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends ElasticsearchRepository<Answer,String> {
    @Query("{ \"multi_match\": { \"fields\":  \"meassage\", \"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    public List<Answer> searchAns(String query);

}
