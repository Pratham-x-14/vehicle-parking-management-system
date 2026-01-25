package com.parking.system.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class ParkResponse {
    private Long ticketId;
    private String slotNumber;
    private String vehicleNumber;
    private LocalDateTime entryTime;

    public ParkResponse() {
    }

    public ParkResponse(Long ticketId, String slotNumber, String vehicleNumber, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.slotNumber = slotNumber;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
    }

    public static ParkResponseBuilder builder() {
        return new ParkResponseBuilder();
    }

    public static class ParkResponseBuilder {
        private Long ticketId;
        private String slotNumber;
        private String vehicleNumber;
        private LocalDateTime entryTime;

        public ParkResponseBuilder ticketId(Long ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public ParkResponseBuilder slotNumber(String slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }

        public ParkResponseBuilder vehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }

        public ParkResponseBuilder entryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public ParkResponse build() {
            return new ParkResponse(ticketId, slotNumber, vehicleNumber, entryTime);
        }
    }

    public Long getTicketId() {
        return ticketId;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
