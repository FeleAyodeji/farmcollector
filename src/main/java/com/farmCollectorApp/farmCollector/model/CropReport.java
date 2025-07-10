package com.farmCollectorApp.farmCollector.model;

import lombok.Getter;

@Getter
public class CropReport {

    private final String cropType;
    private final Double expectedTotal;
    private final Double actualTotal;

    public CropReport(String cropType, Double expectedTotal, Double actualTotal) {
        this.cropType = cropType;
        this.expectedTotal = expectedTotal;
        this.actualTotal = actualTotal;
    }
}
