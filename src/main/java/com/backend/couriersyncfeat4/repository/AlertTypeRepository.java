package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.AlertTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertTypeRepository extends JpaRepository<AlertTypeEntity, Integer> {
}
