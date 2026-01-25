package com.parking.system.dto;

import com.parking.system.enums.VehicleType;

import lombok.Data;

public class UpdateVehicleRequest {
	private String vehicleNumber;
	private VehicleType vehicleType;

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}
}
