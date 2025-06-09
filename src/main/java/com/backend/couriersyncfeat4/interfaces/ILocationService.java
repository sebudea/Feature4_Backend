package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.LocationEntity;

import java.util.List;

public interface ILocationService {

    CustomResponseEntity addLocation(LocationEntity locationEntity);
    List<LocationEntity> findAllLocations();
    LocationEntity findLocationById(Long id);
    CustomResponseEntity updateLocation(LocationEntity locationEntity);
    CustomResponseEntity deleteLocationById(Long id);
    //findLastLocationByPackage
    //findPackagesNotUpdatedInDate


}
