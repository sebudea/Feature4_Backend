package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.interfaces.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PackageController {

    IPackageService packageService;

    @Autowired
    public PackageController(IPackageService packageService) {
        this.packageService = packageService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse addPackage(@Argument PackageEntity packag){
        return packageService.addPackage(packag);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public List<PackageEntity> findAllPackages() {
        return packageService.findAllPackages();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public PackageEntity findPackageById(@Argument int id) {
        return packageService.findPackageById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse updatePackage(@Argument PackageEntity packag){
        return packageService.updatePackage(packag);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse deletePackageById(@Argument int id){
        return packageService.deletePackageById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public PackageEntity findPackageByTrackingCode(@Argument String code){
        return packageService.findPackageByTrackingCode(code);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public List<PackageEntity> findPackagesByDateRange(@Argument LocalDateTime startDate, @Argument LocalDateTime endDate){
        return packageService.findPackagesByDateRange(startDate, endDate);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public PackageCountByUserDTO findPackageCountByUserId (@Argument int id) {
        return packageService.findPackageCountByUserId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public List<PackageEntity> findPackagesByStatusIn(@Argument List<Integer> packageStatuses) {
        return packageService.findPackagesByStatusIn(packageStatuses);
    }

}
