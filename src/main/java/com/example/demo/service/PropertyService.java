package com.example.demo.service;

import com.example.demo.dto.SearchRequest;
import com.example.demo.dto.SearchResponse;
import com.example.demo.model.Property;

import java.util.List;

public interface PropertyService {
    Property saveProperty(Property property);
    List<SearchResponse> searchProperties(String query);
    List<SearchResponse> searchPropertiesByDescription(String query);
}
