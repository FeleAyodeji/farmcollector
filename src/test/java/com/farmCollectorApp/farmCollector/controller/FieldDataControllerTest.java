package com.farmCollectorApp.farmCollector.controller;

import com.farmCollectorApp.farmCollector.model.CropReport;
import com.farmCollectorApp.farmCollector.model.FarmReport;
import com.farmCollectorApp.farmCollector.model.FieldData;
import com.farmCollectorApp.farmCollector.service.FieldDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
                .season("2024")
                .farmName("FarmA")
                .cropType("Wheat")
                .plantingArea(10.0)
                .expectedProduct(100.0)
                .actualProduct(90.0)
                .build();
        FieldData saved = FieldData.builder()
                .id(1L)
                .season("2024")
                .farmName("FarmA")
                .cropType("Wheat")
                .plantingArea(10.0)
                .expectedProduct(100.0)
                .actualProduct(90.0)
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
                .season("2024")
                .farmName("FarmB")
                .cropType("Maize")
                .plantingArea(12.0)
                .expectedProduct(120.0)
                .actualProduct(110.0)
                .build();
        FieldData saved = FieldData.builder()
                .id(2L)
                .season("2024")
                .farmName("FarmB")
                .cropType("Maize")
                .plantingArea(12.0)
                .expectedProduct(120.0)
                .actualProduct(110.0)
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
        List<FarmReport> reports = List.of(new FarmReport("FarmA", 10.0, 8.0));
        when(service.getFarmReport("2024")).thenReturn(reports);

        List<FarmReport> result = controller.reportByFarm("2024");

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
        List<CropReport> reports = List.of(new CropReport("Wheat", 20.0, 18.0));
        when(service.getCropReport("2024")).thenReturn(reports);

        List<CropReport> result = controller.reportByCrop("2024");

        assertEquals(reports, result);
    }

    @Test
    void reportByCrop_noData_returnsEmptyList() {
        when(service.getCropReport("empty")).thenReturn(Collections.emptyList());

        List<CropReport> result = controller.reportByCrop("empty");

        assertTrue(result.isEmpty());
    }
}