package com.backend.couriersyncfeat4.repository;

import com.backend.couriersyncfeat4.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    SystemUser findByEmail(String email);
}
