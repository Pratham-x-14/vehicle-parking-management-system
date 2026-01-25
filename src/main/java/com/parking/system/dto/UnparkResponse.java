package com.parking.system.dto;

import java.time.LocalDateTime;

public class UnparkResponse {
    private Long ticketId;
    private String vehicleNumber;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double totalAmount;
    private String itemizedAmount;

    public UnparkResponse() {
    }

    public UnparkResponse(Long ticketId, String vehicleNumber, LocalDateTime entryTime, LocalDateTime exitTime,
            Double totalAmount, String itemizedAmount) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.totalAmount = totalAmount;
        this.itemizedAmount = itemizedAmount;
    }

    public static UnparkResponseBuilder builder() {
        return new UnparkResponseBuilder();
    }

    public static class UnparkResponseBuilder {
        private Long ticketId;
        private String vehicleNumber;
        private LocalDateTime entryTime;
        private LocalDateTime exitTime;
        private Double totalAmount;
        private String itemizedAmount;

        public UnparkResponseBuilder ticketId(Long ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public UnparkResponseBuilder vehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }

        public UnparkResponseBuilder entryTime(LocalDateTime entryTime) {
            this.entryTime = entryTime;
            return this;
        }

        public UnparkResponseBuilder exitTime(LocalDateTime exitTime) {
            this.exitTime = exitTime;
            return this;
        }

        public UnparkResponseBuilder totalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public UnparkResponseBuilder itemizedAmount(String itemizedAmount) {
            this.itemizedAmount = itemizedAmount;
            return this;
        }

        public UnparkResponse build() {
            return new UnparkResponse(ticketId, vehicleNumber, entryTime, exitTime, totalAmount, itemizedAmount);
        }
    }

    public Long getTicketId() {
        return ticketId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getItemizedAmount() {
        return itemizedAmount;
    }
}
