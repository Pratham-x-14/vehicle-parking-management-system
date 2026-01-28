package com.parking.system.dto;

import com.parking.system.enums.VehicleType;

import lombok.Data;

@Data
public class UpdateVehicleRequest {
	private String vehicleNumber;
	private VehicleType vehicleType;
}
