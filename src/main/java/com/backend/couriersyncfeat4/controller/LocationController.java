package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.CustomResponseEntity;
import com.backend.couriersyncfeat4.entity.LocationEntity;
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

    private final ILocationService locationService;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponseEntity addLocation(@Argument LocationEntity location){
        return locationService.addLocation(location);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public List<LocationEntity> findAllLocations() {
        return locationService.findAllLocations();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @QueryMapping
    public LocationEntity findLocationById(@Argument Long id) {
        return locationService.findLocationById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponseEntity updateLocation(@Argument LocationEntity location){
        return locationService.updateLocation(location);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE')")
    @MutationMapping
    public CustomResponseEntity deleteLocationById(@Argument Long id){
        return locationService.deleteLocationById(id);
    }
}
