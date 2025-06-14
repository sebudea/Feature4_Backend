package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.AlertEntity;
import com.backend.couriersyncfeat4.interfaces.IAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AlertController {

    private final IAlertService alertService;

    @Autowired
    public AlertController(IAlertService alertService) {
        this.alertService = alertService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public List<AlertEntity> findAllAlerts(){
        return alertService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public List<AlertEntity> findAllAlertsByUserId(@Argument Long id){
        return alertService.findAllAlertsByUserId(id);
    }
}
