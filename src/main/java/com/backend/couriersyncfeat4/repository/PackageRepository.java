package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.entity.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {

    PackageEntity findByTrackingCode(UUID code);

    List<PackageEntity> findByRegisteredAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<PackageEntity> findByRegisteredAtAfter(LocalDateTime startDate);

    List<PackageEntity> findByRegisteredAtBefore(LocalDateTime endDate);

    @Query("SELECT new com.backend.couriersyncfeat4.dto.PackageCountByUserDTO(p.user.id, COUNT(p)) " +
            "FROM PackageEntity p WHERE p.user.id = :userId " +
            "GROUP BY p.user.id")
    PackageCountByUserDTO findCountByUserId(@Param("userId") int userId);


    List<PackageEntity> findByStatusIn(List<PackageStatus> packageStatuses);

}
