package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.Location;

import java.util.List;

public interface ILocationService {

    CustomResponse addLocation(Location location);
    List<Location> findAllLocations();
    Location findLocationById(int id);
    CustomResponse updateLocation(Location location);
    CustomResponse deleteLocationById(int id);
    //findLastLocationByPackage
    //findPackagesNotUpdatedInDate


}
