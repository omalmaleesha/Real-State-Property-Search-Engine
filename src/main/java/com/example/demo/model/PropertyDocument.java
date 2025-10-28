package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "property")
public class PropertyDocument {
    @Id
    private Long id;
    private String title;
    @Field(type = FieldType.Text)
    private String description;
    private Double price;

    @GeoPointField
    private GeoPoint location;
}
