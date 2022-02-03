package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.Organisation;
import com.example.elasticsearch.document.Question;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends ElasticsearchRepository<Question,String> {

    @Query("{ \"multi_match\": { \"fields\":  \"text\", \"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    public List<Question> searchQues(String query);

}
