package com.parking.system.dto;

public class UnparkRequest {
    @jakarta.validation.constraints.NotNull
    private String vehicleNumber;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
