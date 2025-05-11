package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Integer> {
}
