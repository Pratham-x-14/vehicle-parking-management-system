package com.parking.system.dto;

import lombok.Data;

@Data
public class UnparkRequest {
    @jakarta.validation.constraints.NotNull
    private String vehicleNumber;
}
