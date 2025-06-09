package com.backend.couriersyncfeat4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackageCountByUserDTO {
    private Long userId;
    private Long packageCount;

}
