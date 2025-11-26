package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.time.Instant;

@Document(indexName = "user_interactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInteractionDocument {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private String actionType;
    private String searchQuery;

    @Field(type = FieldType.Text)
    private String metadataJson;

    private Instant timestamp;

}

