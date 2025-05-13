package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.CustomResponse;
import com.backend.couriersyncfeat4.entity.Location;
import com.backend.couriersyncfeat4.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    ILocationService locationService;

    @MutationMapping
    public CustomResponse addLocation(@Argument Location location){
        return locationService.addLocation(location);
    }

    @QueryMapping
    public List<Location> findAllLocations() {
        return locationService.findAllLocations();
    }

    @QueryMapping
    public Location findLocationById(@Argument int id) {
        return locationService.findLocationById(id);
    }

    @MutationMapping
    public CustomResponse updateLocation(@Argument Location location){
        return locationService.updateLocation(location);
    }

    @MutationMapping
    public CustomResponse deleteLocationById(@Argument int id){
        return locationService.deleteLocationById(id);
    }
}
