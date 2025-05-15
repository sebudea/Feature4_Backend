package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.Location;
import com.backend.couriersyncfeat4.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationController {

    ILocationService locationService;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse addLocation(@Argument Location location){
        return locationService.addLocation(location);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public List<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public Location findLocationById(@Argument int id) {
        return locationService.findLocationById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse updateLocation(@Argument Location location){
        return locationService.updateLocation(location);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponse deleteLocationById(@Argument int id){
        return locationService.deleteLocationById(id);
    }
}
