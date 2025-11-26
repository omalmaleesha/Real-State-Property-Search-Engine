package com.example.demo.dto;


import java.time.Instant;
import java.util.Map;

public class UserInteractionDto {
    public String userId;
    public String propertyId;
    public String actionType;    // CLICK, VIEW, SEARCH, SAVE, MAP_OPEN, etc.
    public String searchQuery;   // optional
    public Map<String, Object> metadata; // device, browser, filters, duration, etc.
    public Instant timestamp;    // optional - server will set if null
}
