package com.example.demo.repository;

import com.example.demo.model.PropertyDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyDocumentRepository extends ElasticsearchRepository<PropertyDocument, Long> {

    List<PropertyDocument> findByTitleContainingIgnoreCase(String title);
    List<PropertyDocument> findByDescriptionContainingIgnoreCase(String description);
    List<PropertyDocument> findByTitleStartingWithIgnoreCase(String prefix);
}

