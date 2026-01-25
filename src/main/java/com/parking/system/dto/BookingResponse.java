package com.parking.system.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long bookingId;
    private String vehicleNumber;
    private String slotNumber;
    private LocalDateTime bookingTime;
    private LocalDateTime validUntil;
    private String status;

    public BookingResponse() {
    }

    public BookingResponse(Long bookingId, String vehicleNumber, String slotNumber, LocalDateTime bookingTime,
            LocalDateTime validUntil, String status) {
        this.bookingId = bookingId;
        this.vehicleNumber = vehicleNumber;
        this.slotNumber = slotNumber;
        this.bookingTime = bookingTime;
        this.validUntil = validUntil;
        this.status = status;
    }

    public static BookingResponseBuilder builder() {
        return new BookingResponseBuilder();
    }

    public static class BookingResponseBuilder {
        private Long bookingId;
        private String vehicleNumber;
        private String slotNumber;
        private LocalDateTime bookingTime;
        private LocalDateTime validUntil;
        private String status;

        public BookingResponseBuilder bookingId(Long bookingId) {
            this.bookingId = bookingId;
            return this;
        }

        public BookingResponseBuilder vehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
            return this;
        }

        public BookingResponseBuilder slotNumber(String slotNumber) {
            this.slotNumber = slotNumber;
            return this;
        }

        public BookingResponseBuilder bookingTime(LocalDateTime bookingTime) {
            this.bookingTime = bookingTime;
            return this;
        }

        public BookingResponseBuilder validUntil(LocalDateTime validUntil) {
            this.validUntil = validUntil;
            return this;
        }

        public BookingResponseBuilder status(String status) {
            this.status = status;
            return this;
        }

        public BookingResponse build() {
            return new BookingResponse(bookingId, vehicleNumber, slotNumber, bookingTime, validUntil, status);
        }
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public String getStatus() {
        return status;
    }
}
