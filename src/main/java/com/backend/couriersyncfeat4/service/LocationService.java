package com.backend.couriersyncfeat4.service;

import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.Location;
import com.backend.couriersyncfeat4.entity.PackageEntity;
import com.backend.couriersyncfeat4.entity.SystemUser;
import com.backend.couriersyncfeat4.interfaces.ILocationService;
import com.backend.couriersyncfeat4.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class LocationService implements ILocationService {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    SystemUserService systemUserService;
    @Autowired
    PackageService packageService;

    // TODO: make validations in each function
    public CustomResponse addLocation(Location location){

        if (location == null) {
            return new CustomResponse(false, "Location is null");
        }

        SystemUser systemUser = systemUserService.findUserById(location.getUser().getId());
        PackageEntity packag = packageService.findPackageById(location.getPackag().getId());

        Location locationAux = new Location();
        locationAux.setUser(systemUser);
        locationAux.setPackag(packag);
        locationAux.setLatitude(location.getLatitude());
        locationAux.setLongitude(location.getLongitude());
        locationAux.setAddress(location.getAddress());

        locationRepository.save(locationAux);
        return new CustomResponse(true, "Location successfully added");
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(int id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public CustomResponse updateLocation(Location location) {
        if (!locationRepository.existsById(location.getId())) {
            return new CustomResponse(false, "Location not found for update");
        }
        if (location.getUser() != null) {
            SystemUser systemUser = systemUserService.findUserById(location.getUser().getId());
            location.setUser(systemUser);
        }
        Location oldLocation = findLocationById(location.getId());
        location.setUser(location.getUser() != null ? location.getUser() : oldLocation.getUser());
        location.setPackag(location.getPackag() != null ? location.getPackag() : oldLocation.getPackag());
        location.setLatitude(location.getLatitude() != null ? location.getLatitude() : oldLocation.getLatitude());
        location.setLongitude(location.getLongitude() != null ? location.getLongitude() : oldLocation.getLongitude());
        location.setAddress(location.getAddress() != null ? location.getAddress() : oldLocation.getAddress());
        location.setUpdatedAt(LocalDateTime.now());

        locationRepository.save(location);
        return new CustomResponse(true, "Location successfully updated");
    }

    public CustomResponse deleteLocationById(int id) {
        if (!locationRepository.existsById(id)) {
            return new CustomResponse(false, "Location with ID " + id + " does not exist");
        }
        locationRepository.deleteById(id);
        return new CustomResponse(true, "Location with ID " + id + " has been successfully deleted ");
    }
}
