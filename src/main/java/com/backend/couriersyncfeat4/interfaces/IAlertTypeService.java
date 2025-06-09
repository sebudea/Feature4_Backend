package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.AlertTypeEntity;

import java.util.List;

public interface IAlertTypeService {

    List<AlertTypeEntity> findAll();
    AlertTypeEntity findById(int id);
}
