package com.farmCollectorApp.farmCollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class CropReport {
    @JsonProperty("crop_type")
    private final String cropType;
    @JsonProperty("expected_total")
    private final Double expectedTotal;
    @JsonProperty("actual_total")
    private final Double actualTotal;

    public CropReport(String cropType, Double expectedTotal, Double actualTotal) {
        this.cropType      = cropType;
        this.expectedTotal = expectedTotal;
        this.actualTotal   = actualTotal;
    }
}
