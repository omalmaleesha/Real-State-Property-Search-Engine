package com.example.demo.controller;

import com.example.demo.config.AppConfig;
import com.example.demo.dto.PropertyInput;
import com.example.demo.dto.SearchResponse;
import com.example.demo.model.Property;
import com.example.demo.model.PropertyDocument;
import com.example.demo.service.impl.PropertyServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyServiceImpl service;
    private final ModelMapper modelMapper;

    public PropertyController(PropertyServiceImpl service,ModelMapper mapper) {
        this.service = service;
        this.modelMapper = mapper;
    }

    @PostMapping
    public Property createProperty(@RequestBody PropertyInput propertyInput) {
        return service.saveProperty(modelMapper.map(propertyInput, Property.class));
    }

    @GetMapping("/search")
    public List<SearchResponse> search(@RequestParam String q) {
        return service.searchProperties(q);
    }

    @GetMapping("/searchDescription")
    public List<SearchResponse> searchDescription(@RequestParam String q) {
        return service.searchPropertiesByDescription(q);
    }

    @GetMapping("/suggest")
    public List<String> suggestTitles(@RequestParam String prefix) {
        return service.suggestTitles(prefix);
    }
}
