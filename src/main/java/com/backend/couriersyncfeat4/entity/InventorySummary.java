package com.backend.couriersyncfeat4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventorySummary {

    private String region;
    private Long inTransit;
    private Long delivered;
    private Long pending;
}
