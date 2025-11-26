package com.example.demo.repository;

import com.example.demo.model.UserInteractionDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface UserInteractionSearchRepository extends ElasticsearchRepository<UserInteractionDocument, String> {
    List<UserInteractionDocument> findByUserId(String userId); // example
}
