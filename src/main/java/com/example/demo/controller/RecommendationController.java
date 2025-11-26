package com.example.demo.controller;

import com.example.demo.dto.UserInteractionDto;
import com.example.demo.model.Property;
import com.example.demo.service.impl.RecommendationServiceImpl;
import com.example.demo.service.impl.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationServiceImpl recommendationService;

    @GetMapping("/{userId}")
    public List<Property> getRecommendations(@PathVariable Long userId) {
        return recommendationService.recommendProperties(userId);
    }

    private final TrackingService trackingService;

    @PostMapping("/track")
    public ResponseEntity<Map<String, Object>> track(
            @RequestBody com.example.demo.dto.UserInteractionDto dto) {

        String id = trackingService.track(dto);

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);

        return ResponseEntity.ok(response);
    }


}

