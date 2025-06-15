package com.backend.couriersyncfeat4.interfaces;

import com.backend.couriersyncfeat4.entity.InventorySummary;

import java.time.LocalDateTime;
import java.util.List;

public interface IInventoryService {
    List<InventorySummary> findSummary(LocalDateTime start,
                                                 LocalDateTime end,
                                                 String region);
}
