package com.backend.couriersyncfeat4.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomResponseEntity {

    private boolean status;
    private String message;

    public CustomResponseEntity(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

}
