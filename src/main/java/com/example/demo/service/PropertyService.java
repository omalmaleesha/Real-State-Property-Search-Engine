package com.example.demo.service;

import com.example.demo.model.Property;
import com.example.demo.model.PropertyDocument;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.PropertySearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertySearchRepository searchRepository;

    public PropertyService(PropertyRepository propertyRepository, PropertySearchRepository searchRepository) {
        this.propertyRepository = propertyRepository;
        this.searchRepository = searchRepository;
    }

    @Transactional
    public Property saveProperty(Property property) {
        Property saved = propertyRepository.save(property);

        PropertyDocument doc = new PropertyDocument(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPrice());
        searchRepository.save(doc);

        return saved;
    }

    public List<PropertyDocument> searchProperties(String query) {
        return searchRepository.findByTitleContaining(query);
    }
}
