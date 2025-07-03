package com.farmCollectorApp.farmCollector.service;


import com.farmCollectorApp.farmCollector.model.CropReport;
import com.farmCollectorApp.farmCollector.model.FarmReport;
import com.farmCollectorApp.farmCollector.model.FieldData;
import com.farmCollectorApp.farmCollector.repository.FieldDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldDataService {

    private final FieldDataRepository repository;

    public FieldDataService(FieldDataRepository repository) {
        this.repository = repository;
    }

    public FieldData save(FieldData data) {
        return repository.save(data);
    }

    public List<FarmReport> getFarmReport(String season) {
        return repository.reportByFarm(season);
    }

    public List<CropReport> getCropReport(String season) {
        return repository.reportByCrop(season);
    }
}
