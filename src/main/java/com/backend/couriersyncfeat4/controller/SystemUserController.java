package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.SystemUser;
import com.backend.couriersyncfeat4.interfaces.IPackageService;
import com.backend.couriersyncfeat4.interfaces.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SystemUserController {

    ISystemUserService systemUserService;

    @Autowired
    public SystemUserController(ISystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<SystemUser> findAllUsers() {
        return systemUserService.findAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public SystemUser findUserById(@Argument int id) {
        return systemUserService.findUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public SystemUser saveUser(@Argument SystemUser user) {
        return systemUserService.saveUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public String currentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return "No autenticado";
        }

        String username = authentication.getName();

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return "User: " + username + ", Roles: " + roles;
    }

}
