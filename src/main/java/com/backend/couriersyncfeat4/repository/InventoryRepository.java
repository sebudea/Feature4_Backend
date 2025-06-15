package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.entity.InventorySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface InventoryRepository extends JpaRepository<PackageEntity, Long> {

    @Query(value = """
        SELECT p.destination                                                       AS region,
               SUM(CASE WHEN ps.name = 'IN_TRANSIT' THEN 1 ELSE 0 END)            AS inTransit,
               SUM(CASE WHEN ps.name = 'DELIVERED'  THEN 1 ELSE 0 END)            AS delivered,
               SUM(CASE WHEN ps.name = 'PENDING'    THEN 1 ELSE 0 END)            AS pending
        FROM   package             p
               JOIN package_status ps ON ps.id = p.status_id
        WHERE p.registered_at >= COALESCE(CAST(? AS TIMESTAMP), p.registered_at)
          AND p.registered_at <= COALESCE(CAST(? AS TIMESTAMP), p.registered_at)
          AND p.destination = COALESCE(CAST(? AS VARCHAR), p.destination)
        GROUP  BY p.destination
        """, nativeQuery = true)
    List<InventorySummary> summaryByPeriodAndRegion(
            @Param("start")  LocalDateTime start,
            @Param("end")    LocalDateTime end,
            @Param("region") String        region);
}
