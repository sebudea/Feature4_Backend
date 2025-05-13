package com.backend.couriersyncfeat4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PackageCountByUserDTO {
    private int userId;
    private int packageCount;

    public PackageCountByUserDTO(int userId, Long count) {
        this.userId = userId;
        this.packageCount = count.intValue();
    }
}
