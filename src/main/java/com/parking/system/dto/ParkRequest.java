package com.parking.system.dto;

import com.parking.system.enums.VehicleType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParkRequest {

    @NotNull
    private String vehicleNumber;

    @NotNull
    private VehicleType vehicleType;
}
