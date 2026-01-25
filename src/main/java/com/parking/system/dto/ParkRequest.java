package com.parking.system.dto;

public class ParkRequest {
    @jakarta.validation.constraints.NotNull
    private String vehicleNumber;
    @jakarta.validation.constraints.NotNull
    private com.parking.system.enums.VehicleType vehicleType;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public com.parking.system.enums.VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(com.parking.system.enums.VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
