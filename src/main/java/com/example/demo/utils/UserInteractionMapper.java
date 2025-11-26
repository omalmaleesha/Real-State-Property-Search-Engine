package com.example.demo.utils;

import com.example.demo.dto.UserInteractionDto;
import com.example.demo.model.UserInteractionDocument;
import com.example.demo.model.UserInteractionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.UUID;

public class UserInteractionMapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static UserInteractionEntity dtoToEntity(UserInteractionDto dto) {

        UserInteractionEntity e = new UserInteractionEntity();

        // Use a real UUID string for primary key
        e.setId(UUID.randomUUID().toString());

        // No conversion. Your entity uses String userId.
        e.setUserId(dto.userId);

        // Same for propertyId
        e.setPropertyId(dto.propertyId);

        // Action Type enum
        if (dto.actionType != null) {
            e.setActionType(com.example.demo.utils.ActionType.valueOf(dto.actionType));
        }

        e.setSearchQuery(dto.searchQuery);

        // metadata -> JSON
        try {
            e.setMetadataJson(dto.metadata == null ? null : MAPPER.writeValueAsString(dto.metadata));
        } catch (JsonProcessingException ex) {
            e.setMetadataJson("{}");
        }

        // timestamp
        e.setTimestamp(dto.timestamp == null ? Instant.now() : dto.timestamp);

        return e;
    }

    public static UserInteractionDocument entityToDocument(UserInteractionEntity e) {

        UserInteractionDocument d = new UserInteractionDocument();

        d.setId(e.getId());
        d.setUserId(e.getUserId());
        d.setPropertyId(e.getPropertyId());
        d.setActionType(e.getActionType() == null ? null : e.getActionType().name());
        d.setSearchQuery(e.getSearchQuery());
        d.setMetadataJson(e.getMetadataJson());
        d.setTimestamp(e.getTimestamp());

        return d;
    }
}
