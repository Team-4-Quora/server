package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.Organisation;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrganisationRepository extends ElasticsearchRepository<Organisation,String> {

        @Query("{ \"multi_match\": { \"fields\":  [\"name\",\"owner\",\"description\"], \"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
        public List<Organisation> searchInOrg(String query);

}
