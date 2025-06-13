package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {

    @Query("""
    SELECT a
    FROM LocationEntity a
    WHERE a.packageEntity.id = :id
    ORDER BY a.updatedAt DESC
""")
    List<LocationEntity> findAllByPackageEntity_Id(Long id);

    List<LocationEntity> findAllByPackageEntity_IdOrderByIdAsc(Long id);

    @Query("""
    SELECT a
    FROM LocationEntity a
    ORDER BY a.updatedAt DESC
""")
    List<LocationEntity> findAllOrderByUpdatedAtDesc();

    @Query("""
    SELECT a
    FROM LocationEntity a
    WHERE a.handlerUser.id = :handlerUserId
    ORDER BY a.updatedAt DESC
""")
    List<LocationEntity> findAllByHandlerUser_Id(Long handlerUserId);
}
