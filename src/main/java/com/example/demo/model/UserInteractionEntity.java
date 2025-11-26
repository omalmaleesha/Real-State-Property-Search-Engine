package com.example.demo.model;

import com.example.demo.utils.ActionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "user_interactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInteractionEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String  id = UUID.randomUUID().toString();

    @Column(name = "user_id")
    private String  userId;

    @Column(name = "property_id")
    private String propertyId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @Column(name = "search_query")
    private String searchQuery;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadataJson;

    @Column(name = "timestamp")
    private Instant timestamp;

}
