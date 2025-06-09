package com.backend.couriersyncfeat4.service;

import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.LocationEntity;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.entity.UserEntity;
import com.backend.couriersyncfeat4.interfaces.ILocationService;
import com.backend.couriersyncfeat4.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;
    private final UserService userService;
    private final PackageService packageService;

    @Autowired
    public LocationService(LocationRepository locationRepository, UserService userService, PackageService packageService) {
        this.locationRepository = locationRepository;
        this.userService = userService;
        this.packageService = packageService;
    }

    // TODO: make validations in each function
    public CustomResponseEntity addLocation(LocationEntity locationEntity){

        if (locationEntity == null || locationEntity.getHandlerUser() == null || locationEntity.getPackageEntity() == null
        || locationEntity.getAddress() == null) {
            return new CustomResponseEntity(false, "LocationEntity, user, package or address are null");
        }

        UserEntity userEntity = userService.findUserById(locationEntity.getHandlerUser().getId());
        PackageEntity packageEntity = packageService.findPackageById(locationEntity.getPackageEntity().getId());

        LocationEntity locationEntityAux = new LocationEntity();
        locationEntityAux.setHandlerUser(userEntity);
        locationEntityAux.setPackageEntity(packageEntity);
        locationEntityAux.setLatitude(locationEntity.getLatitude());
        locationEntityAux.setLongitude(locationEntity.getLongitude());
        locationEntityAux.setAddress(locationEntity.getAddress());
        locationEntityAux.setUpdatedAt(LocalDateTime.now());

        locationRepository.save(locationEntityAux);
        return new CustomResponseEntity(true, "LocationEntity successfully added");
    }

    public List<LocationEntity> findAllLocations() {
        return locationRepository.findAll();
    }

    public LocationEntity findLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LocationEntity not found"));
    }

    public CustomResponseEntity updateLocation(LocationEntity locationEntity) {
        if (!locationRepository.existsById(locationEntity.getId())) {
            return new CustomResponseEntity(false, "LocationEntity not found for update");
        }

        LocationEntity oldLocationEntity = findLocationById(locationEntity.getId());

        UserEntity userEntity = (locationEntity.getHandlerUser() != null) ? locationEntity.getHandlerUser() : oldLocationEntity.getHandlerUser();

        PackageEntity packageEntity = (locationEntity.getPackageEntity() != null) ? locationEntity.getPackageEntity() : oldLocationEntity.getPackageEntity();

        Float latitude = (locationEntity.getLatitude() != null) ? locationEntity.getLatitude() : oldLocationEntity.getLatitude();

        Float longitude = (locationEntity.getLongitude() != null) ? locationEntity.getLongitude() : oldLocationEntity.getLongitude();

        String address = (locationEntity.getAddress() != null) ? locationEntity.getAddress() : oldLocationEntity.getAddress();

        oldLocationEntity.setHandlerUser(userEntity);
        oldLocationEntity.setPackageEntity(packageEntity);
        oldLocationEntity.setLatitude(latitude);
        oldLocationEntity.setLongitude(longitude);
        oldLocationEntity.setAddress(address);
        oldLocationEntity.setUpdatedAt(LocalDateTime.now());

        locationRepository.save(oldLocationEntity);
        return new CustomResponseEntity(true, "LocationEntity successfully updated");
    }


    public CustomResponseEntity deleteLocationById(Long id) {
        if (!locationRepository.existsById(id)) {
            return new CustomResponseEntity(false, "LocationEntity with ID " + id + " does not exist");
        }
        locationRepository.deleteById(id);
        return new CustomResponseEntity(true, "LocationEntity with ID " + id + " has been successfully deleted ");
    }
}
