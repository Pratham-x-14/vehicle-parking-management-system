package com.parking.system.config;

import com.parking.system.entity.ParkingSlot;
import com.parking.system.enums.VehicleType;
import com.parking.system.repository.ParkingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ParkingSlotRepository slotRepository;

    public DatabaseSeeder(ParkingSlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (slotRepository.count() == 0) {
            ParkingSlot s1 = new ParkingSlot(null, "A-1", null, VehicleType.CAR, true, false);
            ParkingSlot s2 = new ParkingSlot(null, "A-2", null, VehicleType.CAR, true, false);
            ParkingSlot s3 = new ParkingSlot(null, "B-1", null, VehicleType.BIKE, true, false);
            ParkingSlot s4 = new ParkingSlot(null, "B-2", null, VehicleType.BIKE, true, false);
            ParkingSlot s5 = new ParkingSlot(null, "C-1", null, VehicleType.TRUCK, true, false);

            slotRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));
            System.out.println("Database seeded with initial parking slots.");
        }
    }
}
