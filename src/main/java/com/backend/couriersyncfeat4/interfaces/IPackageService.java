package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.PackageEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface IPackageService {

    CustomResponseEntity addPackage(PackageEntity packag);
    List<PackageEntity> findAllPackages();
    PackageEntity findPackageById(Long id);
    PackageEntity findPackageByTrackingCode(String code);
    CustomResponseEntity updatePackage(PackageEntity packag);
    CustomResponseEntity deletePackageById(Long id);

    //TODO: Check these lines of codes
    List<PackageEntity> findPackagesByDateRange (LocalDateTime startDate, LocalDateTime endDate);
    PackageCountByUserDTO findPackageCountByUserId (Long id);
    List<PackageEntity> findPackagesByStatusIn(List<Integer> packageStatuses);
    List<PackageCountByUserDTO> findCountByAllUsers();
}
