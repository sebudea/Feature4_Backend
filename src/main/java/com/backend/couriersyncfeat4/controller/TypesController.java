package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.AlertTypeEntity;
import com.backend.couriersyncfeat4.entity.PackageStatusEntity;
import com.backend.couriersyncfeat4.entity.RoleEntity;
import com.backend.couriersyncfeat4.interfaces.IAlertTypeService;
import com.backend.couriersyncfeat4.interfaces.IPackageStatusService;
import com.backend.couriersyncfeat4.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TypesController {

    private final IRoleService roleService;
    private final IPackageStatusService  packageStatusService;
    private final IAlertTypeService alertTypeService;

    @Autowired
    public TypesController(IRoleService roleService, IPackageStatusService  packageStatusService, IAlertTypeService alertTypeService){
        this.roleService = roleService;
        this.packageStatusService = packageStatusService;
        this.alertTypeService = alertTypeService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public List<RoleEntity> findAllRoles(){
        return roleService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public RoleEntity findRoleById(@Argument int id){
        return roleService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public List<PackageStatusEntity> findAllPackagesStatus(){
        return packageStatusService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public PackageStatusEntity findPackageStatusById(@Argument int id){
        return packageStatusService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public List<AlertTypeEntity> findAllAlertTypes(){
        return alertTypeService.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LOGISTICS', 'WAREHOUSE', 'SUPERVISOR')")
    @QueryMapping
    public AlertTypeEntity findAlertTypeById(@Argument int id){
        return alertTypeService.findById(id);
    }
}
