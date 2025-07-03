package com.farmCollectorApp.farmCollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class FarmReport {
    @JsonProperty("farm_name")
    private final String farmName;
    @JsonProperty("expected_total")
    private final Double expectedTotal;
    @JsonProperty("actual_total")
    private final Double actualTotal;

    public FarmReport(String farmName, Double expectedTotal, Double actualTotal) {
        this.farmName      = farmName;
        this.expectedTotal = expectedTotal;
        this.actualTotal   = actualTotal;
    }
}
