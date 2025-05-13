package com.backend.couriersyncfeat4.service;

import com.backend.couriersyncfeat4.dto.PackageCountByUserDTO;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.interfaces.IPackageService;
import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.PackageStatus;
import com.backend.couriersyncfeat4.entity.SystemUser;
import com.backend.couriersyncfeat4.repository.PackageRepository;
import com.backend.couriersyncfeat4.repository.PackageStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PackageService implements IPackageService {

    @Autowired
    PackageRepository packageRepository;
    @Autowired
    SystemUserService systemUserService;
    @Autowired
    PackageStatusRepository packageStatusRepository;

    // TODO: make validations in each function
    public CustomResponse addPackage(PackageEntity packag) {

        if (packag == null) {
            return new CustomResponse(false, "Package is null");
        }

        SystemUser systemUser = systemUserService.findUserById(packag.getUser().getId());
        PackageStatus packageStatus = findPackageStatusById(packag.getStatus().getId());

        PackageEntity packagAux = new PackageEntity();
        packagAux.setUser(systemUser);
        packagAux.setStatus(packageStatus);
        packagAux.setDescription(packag.getDescription());
        packagAux.setDestination(packag.getDestination());

        packageRepository.save(packagAux);
        return new CustomResponse(true, "Package successfully added");
    }

    public List<PackageEntity> findAllPackages() {
        return packageRepository.findAll();
    }

    public PackageEntity findPackageById(int id) {
        return packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
    }

    public PackageEntity findPackageByTrackingCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }

        UUID trackingCode = UUID.fromString(code);
        return packageRepository.findByTrackingCode(trackingCode);
    }

    public CustomResponse updatePackage(PackageEntity packag) {
        if (!packageRepository.existsById(packag.getId())) {
            return new CustomResponse(false, "Package not found for update");
        }
        if (packag.getUser() != null) {
            SystemUser systemUser = systemUserService.findUserById(packag.getUser().getId());
            packag.setUser(systemUser);
        }
        if (packag.getStatus() != null) {
            PackageStatus packageStatus = findPackageStatusById(packag.getStatus().getId());
            packag.setStatus(packageStatus);
        }
        PackageEntity oldPackage = findPackageById(packag.getId());
        packag.setUser(packag.getUser() != null ? packag.getUser() : oldPackage.getUser());
        packag.setStatus(packag.getStatus() != null ? packag.getStatus() : oldPackage.getStatus());
        packag.setTrackingCode(packag.getTrackingCode());
        packag.setDescription(packag.getDescription() != null ? packag.getDescription() : oldPackage.getDescription());
        packag.setRegisteredAt(oldPackage.getRegisteredAt());
        packag.setDestination(packag.getDestination() != null ? packag.getDestination() : oldPackage.getDestination());

        packageRepository.save(packag);
        return new CustomResponse(true, "Package successfully updated");
    }

    public CustomResponse deletePackageById(int id) {
        if (!packageRepository.existsById(id)) {
            return new CustomResponse(false, "Package with id " + id + " does not exist");
        }
        packageRepository.deleteById(id);
        return new CustomResponse(true, "Package with id " + id + " successfully deleted");
    }

    public PackageStatus findPackageStatusById(int id) {
        return packageStatusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package Status not found"));
    }

    public List<PackageEntity> findPackagesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            return packageRepository.findByRegisteredAtBetween(startDate, endDate);
        } else if (startDate != null) {
            return packageRepository.findByRegisteredAtAfter(startDate);
        } else if (endDate != null) {
            return packageRepository.findByRegisteredAtBefore(endDate);
        }
        return null;
    }

    public PackageCountByUserDTO findPackageCountByUserId(int id){
        PackageCountByUserDTO packageCountByUserDTO = packageRepository.findCountByUserId(id);
        if (packageCountByUserDTO == null) {
            packageCountByUserDTO = new PackageCountByUserDTO();
            packageCountByUserDTO.setUserId(id);
            packageCountByUserDTO.setPackageCount(0);
        }
        return packageCountByUserDTO;
    }

    public List<PackageEntity> findPackagesByStatusIn(List<Integer> packageStatusesId) {
        if (packageStatusesId.isEmpty()) {
            throw new IllegalArgumentException("La lista de estados no puede ser nula o vacía");
        }
        List<PackageStatus> packageStatuses = new ArrayList<>();
        for(Integer statusId : packageStatusesId){
            packageStatuses.add(findPackageStatusById(statusId));
        }
        return packageRepository.findByStatusIn(packageStatuses);
    }



}
