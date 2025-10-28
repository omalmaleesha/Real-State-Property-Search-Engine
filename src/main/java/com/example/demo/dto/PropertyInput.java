package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyInput {
    private String title;
    private String description;
    private Double price;
    private GeoPoint location;
}
