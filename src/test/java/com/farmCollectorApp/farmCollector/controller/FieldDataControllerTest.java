package com.farmCollectorApp.farmCollector.controller;

import com.farmCollectorApp.farmCollector.model.CropReport;
import com.farmCollectorApp.farmCollector.model.FarmReport;
import com.farmCollectorApp.farmCollector.model.FieldData;
import com.farmCollectorApp.farmCollector.service.FieldDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FieldDataControllerTest {

    @Mock
    private FieldDataService service;

    @InjectMocks
    private FieldDataController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePlanted_validData_returnsSavedData() {
        FieldData input = FieldData.builder()
                .season("test-season")
                .farmName("test-farm")
                .cropType("test-crop")
                .plantingArea(1.0)
                .expectedProduct(100.0)
                .actualProduct(null) // optional for planting
                .build();
        FieldData saved = FieldData.builder()
                .id(1L)
                .season("test-season")
                .farmName("test-farm")
                .cropType("test-crop")
                .plantingArea(1.0)
                .expectedProduct(100.0)
                .actualProduct(null)
                .build();
        when(service.save(input)).thenReturn(saved);

        ResponseEntity<FieldData> response = controller.savePlanted(input);

        assertEquals(saved, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void savePlanted_nullData_throwsException() {
        assertThrows(NullPointerException.class, () -> controller.savePlanted(null));
    }

    @Test
    void saveHarvested_validData_returnsSavedData() {
        FieldData input = FieldData.builder()
                .season("test-season")
                .farmName("test-farm")
                .cropType("test-crop")
                .plantingArea(1.0)
                .expectedProduct(100.0)
                .actualProduct(80.0)
                .build();
        FieldData saved = FieldData.builder()
                .id(2L)
                .season("test-season")
                .farmName("test-farm")
                .cropType("test-crop")
                .plantingArea(1.0)
                .expectedProduct(100.0)
                .actualProduct(80.0)
                .build();
        when(service.save(input)).thenReturn(saved);

        ResponseEntity<FieldData> response = controller.saveHarvested(input);

        assertEquals(saved, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void saveHarvested_nullData_throwsException() {
        assertThrows(NullPointerException.class, () -> controller.saveHarvested(null));
    }

    @Test
    void reportByFarm_validSeason_returnsReport() {
        List<FarmReport> reports = List.of(new FarmReport("test-farm", 100.0, 80.0));
        when(service.getFarmReport("test-season")).thenReturn(reports);

        List<FarmReport> result = controller.reportByFarm("test-season");

        assertEquals(reports, result);
    }

    @Test
    void reportByFarm_noData_returnsEmptyList() {
        when(service.getFarmReport("empty")).thenReturn(Collections.emptyList());

        List<FarmReport> result = controller.reportByFarm("empty");

        assertTrue(result.isEmpty());
    }

    @Test
    void reportByCrop_validSeason_returnsReport() {
        List<CropReport> reports = List.of(new CropReport("test-crop", 100.0, 80.0));
        when(service.getCropReport("test-season")).thenReturn(reports);

        List<CropReport> result = controller.reportByCrop("test-season");

        assertEquals(reports, result);
    }

    @Test
    void reportByCrop_noData_returnsEmptyList() {
        when(service.getCropReport("empty")).thenReturn(Collections.emptyList());

        List<CropReport> result = controller.reportByCrop("empty");

        assertTrue(result.isEmpty());
    }
}
