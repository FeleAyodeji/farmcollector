package com.farmCollectorApp.farmCollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class FarmReport {

    private final String farmName;

    private final Double expectedTotal;

    private final Double actualTotal;

    public FarmReport(String farmName, Double expectedTotal, Double actualTotal) {
        this.farmName      = farmName;
        this.expectedTotal = expectedTotal;
        this.actualTotal   = actualTotal;
    }
}
