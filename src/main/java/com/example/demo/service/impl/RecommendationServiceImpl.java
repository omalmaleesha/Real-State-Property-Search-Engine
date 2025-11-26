package com.example.demo.service.impl;

import com.example.demo.model.Property;
import com.example.demo.model.UserInteractionEntity;
import com.example.demo.model.UserInteractionEntity;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.UserInteractionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl {

    private final UserInteractionRepository interactionRepository;
    private final PropertyRepository propertyRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${groq.api.key}")
    private String groqApiKey;

    public List<Property> recommendProperties(Long userId) {
        List<UserInteractionEntity> interactions = interactionRepository.findByUserId(String.valueOf(userId));

        if (interactions.isEmpty()) {
            return (List<Property>) propertyRepository.findAll(); // fallback
        }

        // Step 1: Build user behavior summary
        StringBuilder behavior = new StringBuilder("User behavior summary:\n");
        for (UserInteractionEntity ui : interactions) {
            behavior.append("- Action: ").append(ui.getActionType())
                    .append(", Search: ").append(ui.getSearchQuery())
                    .append("\n");
        }

        // Step 2: Send summary to Groq API
        String prompt = "Based on this user behavior, describe the ideal property search filter (e.g. 'apartments under 30000000 in Colombo near sea'):\n"
                + behavior;

        String groqResponse = callGroqApi(prompt);

        // Step 3: Query Elasticsearch for matching properties
        // For simplicity, we'll search by title or description
        List<Property> allProps = propertyRepository.findAll();
        List<Property> filtered = new ArrayList<>();
        for (Property prop : allProps) {
            if (groqResponse.toLowerCase().contains(prop.getTitle().toLowerCase()) ||
                    groqResponse.toLowerCase().contains(prop.getDescription().toLowerCase())) {
                filtered.add(prop);
            }
        }

        return filtered;
    }

    private String callGroqApi(String prompt) {
        String url = "https://api.groq.com/openai/v1/chat/completions";
        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3-8b-8192");
        request.put("messages", List.of(Map.of("role", "user", "content", prompt)));

        var headers = new org.springframework.http.HttpHeaders();
        headers.set("Authorization", "Bearer " + groqApiKey);
        headers.set("Content-Type", "application/json");

        var entity = new org.springframework.http.HttpEntity<>(request, headers);

        try {
            var response = restTemplate.postForEntity(url, entity, Map.class);
            Map<?, ?> choices = (Map<?, ?>) ((List<?>) response.getBody().get("choices")).get(0);
            return ((Map<?, ?>) choices.get("message")).get("content").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "apartments in Colombo under 30000000"; // fallback default
        }
    }
}

