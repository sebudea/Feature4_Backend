package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.PackageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageStatusRepository extends JpaRepository<PackageStatus, Integer> {
}
