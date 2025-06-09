package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.RoleEntity;

import java.util.List;

public interface IRoleService {

    List<RoleEntity> findAll();
    RoleEntity findById(int id);

}
