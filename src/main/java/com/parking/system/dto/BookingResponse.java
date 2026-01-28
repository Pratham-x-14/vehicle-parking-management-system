package com.parking.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private Long bookingId;
    private String vehicleNumber;
    private String slotNumber;
    private LocalDateTime bookingTime;
    private LocalDateTime validUntil;
    private String status;
}
