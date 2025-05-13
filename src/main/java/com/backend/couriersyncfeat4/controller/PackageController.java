package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.interfaces.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PackageController {

    @Autowired
    IPackageService packageService;

    @MutationMapping
    public CustomResponse addPackage(@Argument PackageEntity packag){
        return packageService.addPackage(packag);
    }

    @QueryMapping
    public List<PackageEntity> findAllPackages() {
        return packageService.findAllPackages();
    }

    @QueryMapping
    public PackageEntity findPackageById(@Argument int id) {
        return packageService.findPackageById(id);
    }

    @MutationMapping
    public CustomResponse updatePackage(@Argument PackageEntity packag){
        return packageService.updatePackage(packag);
    }

    @MutationMapping
    public CustomResponse deletePackageById(@Argument int id){
        return packageService.deletePackageById(id);
    }

    @QueryMapping
    public PackageEntity findPackageByTrackingCode(@Argument String code){
        return packageService.findPackageByTrackingCode(code);
    }

    @QueryMapping
    public List<PackageEntity> findPackagesByDateRange(@Argument LocalDateTime startDate, @Argument LocalDateTime endDate){
        return packageService.findPackagesByDateRange(startDate, endDate);
    }

    @QueryMapping
    public PackageCountByUserDTO findPackageCountByUserId (@Argument int id) {
        return packageService.findPackageCountByUserId(id);
    }

    @QueryMapping
    public List<PackageEntity> findPackagesByStatusIn(@Argument List<Integer> packageStatuses) {
        return packageService.findPackagesByStatusIn(packageStatuses);
    }

}
