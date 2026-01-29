package com.parking.system.dto;

import com.parking.system.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotDTO {

    private Long id;
    private String slotNumber;
    private String vehicleNumber;
    private VehicleType type;
    private boolean available;
}
