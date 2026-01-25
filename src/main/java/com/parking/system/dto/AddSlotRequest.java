package com.parking.system.dto;

import com.parking.system.enums.VehicleType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddSlotRequest {
    @NotNull(message = "Slot number is required")
    private String slotNumber;

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
