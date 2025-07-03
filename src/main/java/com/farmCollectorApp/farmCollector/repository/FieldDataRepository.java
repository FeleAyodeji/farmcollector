package com.farmCollectorApp.farmCollector.repository;


import com.farmCollectorApp.farmCollector.model.CropReport;
import com.farmCollectorApp.farmCollector.model.FarmReport;
import com.farmCollectorApp.farmCollector.model.FieldData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldDataRepository extends JpaRepository<FieldData, Long> {
    List<FieldData> findBySeason(String season);

    @Query("""
              SELECT f.farmName        AS farmName,
                     SUM(f.expectedProduct) AS expectedTotal,
                     SUM(f.actualProduct)   AS actualTotal
              FROM FieldData f
              WHERE f.season = :season
              GROUP BY f.farmName
            """)
    List<FarmReport> reportByFarm(@Param("season") String season);

    @Query("""
              SELECT f.cropType        AS cropType,
                     SUM(f.expectedProduct) AS expectedTotal,
                     SUM(f.actualProduct)   AS actualTotal
              FROM FieldData f
              WHERE f.season = :season
              GROUP BY f.cropType
            """)
    List<CropReport> reportByCrop(@Param("season") String season);
}
