package com.parking.system.entity;

import com.parking.system.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "parking_slots")
// @Data -- Lombok removed
// @NoArgsConstructor
// @AllArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String slotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType slotType;

    @Column(nullable = false)
    private boolean isAvailable = true;

    @Column(nullable = false)
    private boolean isReserved = false;

    public ParkingSlot() {
    }

    public ParkingSlot(Long id, String slotNumber, VehicleType slotType, boolean isAvailable, boolean isReserved) {
        this.id = id;
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        this.isAvailable = isAvailable;
        this.isReserved = isReserved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public void setSlotType(VehicleType slotType) {
        this.slotType = slotType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public String toString() {
        return "ParkingSlot{id=" + id + ", slotNumber='" + slotNumber + "', slotType=" + slotType + ", isAvailable="
                + isAvailable + ", isReserved=" + isReserved + "}";
    }
}
