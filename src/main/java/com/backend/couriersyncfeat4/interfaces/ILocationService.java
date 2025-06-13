package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.LocationEntity;

import java.util.List;

public interface ILocationService {

    LocationEntity addLocation(LocationEntity locationEntity);
    List<LocationEntity> findAllLocations();
    LocationEntity findLocationById(Long id);
    CustomResponseEntity updateLocation(LocationEntity locationEntity);
    CustomResponseEntity deleteLocationById(Long id);
    List<LocationEntity> findAllLocationsByPackageEntityId(Long packageId);
    LocationEntity findLastLocationByPackageEntityId(Long packageId);
    List<LocationEntity> findAllLocationsByUserId(Long userId);
    //findLastLocationByPackage

}
