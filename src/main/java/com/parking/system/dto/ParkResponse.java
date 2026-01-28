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
public class ParkResponse {

    private Long ticketId;
    private String slotNumber;
    private String vehicleNumber;
    private LocalDateTime entryTime;
}
