package com.parking.system.dto;

import com.parking.system.enums.VehicleType;
import lombok.Data;

@Data
public class BookingRequest {
    private String vehicleNumber;
    private VehicleType vehicleType;
}
