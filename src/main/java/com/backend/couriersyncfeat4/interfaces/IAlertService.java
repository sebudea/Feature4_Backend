package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.AlertEntity;

import java.util.List;

public interface IAlertService {

    AlertEntity createAlert(Long userId, Long packageId, int alertTypeId, String description);
    List<AlertEntity> findAll();
    List<AlertEntity> findAllAlertsByUserId(Long userId);

}
