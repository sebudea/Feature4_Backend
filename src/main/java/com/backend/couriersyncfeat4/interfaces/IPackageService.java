package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.entity.PackageStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IPackageService {

    CustomResponse addPackage(PackageEntity packag);
    List<PackageEntity> findAllPackages();
    PackageEntity findPackageById(int id);
    PackageEntity findPackageByTrackingCode(String code);
    CustomResponse updatePackage(PackageEntity packag);
    CustomResponse deletePackageById(int id);

    //TODO: Check these lines of codes
    List<PackageEntity> findPackagesByDateRange (LocalDateTime startDate, LocalDateTime endDate);
    PackageCountByUserDTO findPackageCountByUserId (int id);
    List<PackageEntity> findPackagesByStatusIn(List<Integer> packageStatuses);

}
