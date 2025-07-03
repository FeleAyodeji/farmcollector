package com.farmCollectorApp.farmCollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("season")
    private String season;

    @JsonProperty("farm_name")
    private String farmName;

    @JsonProperty("crop_type")
    private String cropType;

    @JsonProperty("planting_area")
    private Double plantingArea;

    @JsonProperty("expected_product")
    private Double expectedProduct;

    @JsonProperty("actual_product")
    private Double actualProduct;
}