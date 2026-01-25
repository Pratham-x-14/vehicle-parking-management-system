package com.parking.system.config;

import com.parking.system.entity.ParkingSlot;
import com.parking.system.enums.VehicleType;
import com.parking.system.repository.ParkingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    private final ParkingSlotRepository parkingSlotRepository;

    public DataInitializer(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (parkingSlotRepository.count() == 0) {
                // Initialize with some default slots
                ParkingSlot[] slots = {
                        new ParkingSlot(null, "B-001", VehicleType.BIKE, true, false),
                        new ParkingSlot(null, "B-002", VehicleType.BIKE, true, false),
                        new ParkingSlot(null, "B-003", VehicleType.BIKE, true, false),
                        new ParkingSlot(null, "B-004", VehicleType.BIKE, true, false),
                        new ParkingSlot(null, "B-005", VehicleType.BIKE, true, false),

                        new ParkingSlot(null, "C-001", VehicleType.CAR, true, false),
                        new ParkingSlot(null, "C-002", VehicleType.CAR, true, false),
                        new ParkingSlot(null, "C-003", VehicleType.CAR, true, false),
                        new ParkingSlot(null, "C-004", VehicleType.CAR, true, false),
                        new ParkingSlot(null, "C-005", VehicleType.CAR, true, false),

                        new ParkingSlot(null, "T-001", VehicleType.TRUCK, true, false),
                        new ParkingSlot(null, "T-002", VehicleType.TRUCK, true, false)
                };

                parkingSlotRepository.saveAll(Arrays.asList(slots));
                System.out.println("Initialized default parking slots.");
            }
        };
    }
}
