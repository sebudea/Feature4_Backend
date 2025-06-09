package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.PackageStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageStatusRepository extends JpaRepository<PackageStatusEntity, Integer> {
}
