package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.PackageStatusEntity;

import java.util.List;

public interface IPackageStatusService {

    List<PackageStatusEntity> findAll();
    PackageStatusEntity findById(int id);
}
