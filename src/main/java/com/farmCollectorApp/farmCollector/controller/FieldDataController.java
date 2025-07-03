package com.farmCollectorApp.farmCollector.controller;

import com.farmCollectorApp.farmCollector.model.CropReport;
import com.farmCollectorApp.farmCollector.model.FarmReport;
import com.farmCollectorApp.farmCollector.model.FieldData;
import com.farmCollectorApp.farmCollector.service.FieldDataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FieldDataController {

    private final FieldDataService service;

    public FieldDataController(FieldDataService service) {
        this.service = service;
    }

    /**
     * Saves planted field data.
     *
     * @param data the field data to save
     * @return the saved field data
     * @throws NullPointerException if the provided data is null
     */
    @PostMapping(value="/planted", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldData> savePlanted(@RequestBody FieldData data) {
        if (data == null) {
            throw new NullPointerException("FieldData cannot be null");
        }
        return ResponseEntity.ok(service.save(data));
    }

    /**
     * Saves harvested field data.
     *
     * @param data the field data to save
     * @return the saved field data
     * @throws NullPointerException if the provided data is null
     */
    @PostMapping("/harvested")
    public ResponseEntity<FieldData> saveHarvested(@RequestBody FieldData data) {
        if (data == null) {
            throw new NullPointerException("FieldData cannot be null");
        }
        return ResponseEntity.ok(service.save(data));
    }

    /**
     * Retrieves all field data.
     *
     * @return a list of all field data
     */
    @GetMapping("/reports/farm/{season}")
    public List<FarmReport> reportByFarm(@PathVariable String season) {
        if (season == null || season.isEmpty()) {
            throw new NullPointerException("Season cannot be null or empty");
        }
        return service.getFarmReport(season);
    }

    /**
     * Retrieves crop reports for a specific season.
     *
     * @param season the season to filter reports by
     * @return a list of crop reports for the specified season
     * @throws NullPointerException if the provided season is null or empty
     */
    @GetMapping("/reports/crops/{season}")
    public List<CropReport> reportByCrop(@PathVariable String season) {
        if (season == null || season.isEmpty()) {
            throw new NullPointerException("Season cannot be null or empty");
        }
        return service.getCropReport(season);
    }
}
