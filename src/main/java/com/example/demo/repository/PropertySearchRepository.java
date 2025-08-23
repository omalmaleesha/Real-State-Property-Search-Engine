package com.example.demo.repository;

import com.example.demo.model.PropertyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PropertySearchRepository extends ElasticsearchRepository<PropertyDocument, Long> {
    List<PropertyDocument> findByTitleContaining(String title);
}
