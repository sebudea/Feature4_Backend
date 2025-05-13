package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.SystemUser;

import java.util.List;

public interface ISystemUserService {

    List<SystemUser> findAllUsers();
    SystemUser findUserById(int id);
    SystemUser saveUser(SystemUser user);

}
