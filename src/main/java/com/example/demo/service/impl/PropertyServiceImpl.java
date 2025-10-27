package com.example.demo.service.impl;

import com.example.demo.dto.SearchRequest;
import com.example.demo.dto.SearchResponse;
import com.example.demo.model.Property;
import com.example.demo.model.PropertyDocument;
import com.example.demo.repository.PropertyDocumentRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.PropertySearchRepository;
import com.example.demo.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertySearchRepository searchRepository;
    private final ModelMapper modelMapper;
    private final PropertyDocumentRepository propertyDocumentRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertySearchRepository searchRepository, ModelMapper modelMapper, PropertyDocumentRepository propertyDocumentRepository) {
        this.propertyRepository = propertyRepository;
        this.searchRepository = searchRepository;
        this.modelMapper = modelMapper;
        this.propertyDocumentRepository = propertyDocumentRepository;
    }

    @Transactional
    public Property saveProperty(Property property) {
        Property saved = propertyRepository.save(property);
        PropertyDocument doc = new PropertyDocument(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPrice());
        searchRepository.save(doc);
        return saved;
    }

    public List<SearchResponse> searchProperties(String query) {
        List<PropertyDocument> byTitleContaining = searchRepository.findByTitleContaining(query);
        Type targetListType = new TypeToken<List<SearchResponse>>() {}.getType();
        return modelMapper.map(byTitleContaining, targetListType);
    }

    @Override
    public List<SearchResponse> searchPropertiesByDescription(String query) {
        List<PropertyDocument> byDescriptionContaining = searchRepository.searchByDescription(query);
        Type targetListType = new TypeToken<List<SearchResponse>>() {}.getType();
        return modelMapper.map(byDescriptionContaining, targetListType);
    }

    @Override
    public List<String> suggestTitles(String prefix) {
        return propertyDocumentRepository
                .findByTitleStartingWithIgnoreCase(prefix)
                .stream()
                .map(PropertyDocument::getTitle)
                .limit(10) // Limit suggestions for performance
                .collect(Collectors.toList());
    }
}