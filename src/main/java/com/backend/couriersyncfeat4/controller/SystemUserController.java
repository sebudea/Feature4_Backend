package com.backend.couriersyncfeat4.controller;

import com.backend.couriersyncfeat4.entity.SystemUser;
import com.backend.couriersyncfeat4.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SystemUserController {

    @Autowired
    SystemUserService systemUserService;

    @QueryMapping
    public List<SystemUser> findAllUsers() {
        return systemUserService.findAllUsers();
    }

    @QueryMapping
    public SystemUser findUserById(@Argument int id) {
        return systemUserService.findUserById(id);
    }

    @MutationMapping
    public SystemUser saveUser(@Argument SystemUser user) {
        return systemUserService.saveUser(user);
    }
}
