package com.parking.system.repository;

import com.parking.system.entity.ParkingTicket;
import com.parking.system.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findByVehicleAndExitTimeIsNull(Vehicle vehicle);

    java.util.List<ParkingTicket> findByVehicle(Vehicle vehicle);
}
