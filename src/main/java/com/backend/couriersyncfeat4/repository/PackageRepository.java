package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.Packag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Packag, Integer> {
}
