package com.example.demo.controller;

import com.example.demo.model.Property;
import com.example.demo.model.PropertyDocument;
import com.example.demo.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return service.saveProperty(property);
    }

    @GetMapping("/search")
    public List<PropertyDocument> search(@RequestParam String q) {
        return service.searchProperties(q);
    }
}
