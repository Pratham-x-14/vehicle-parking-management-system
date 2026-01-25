package com.parking.system.enums;

public enum VehicleType {
    BIKE(1),
    CAR(1),
    TRUCK(2);

    private final int spotsNeeded;

    VehicleType(int spotsNeeded) {
        this.spotsNeeded = spotsNeeded;
    }

    public int getSpotsNeeded() {
        return spotsNeeded;
    }
}
