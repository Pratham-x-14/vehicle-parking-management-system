package com.parking.system.repository;

import com.parking.system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByVehicleNumberAndStatus(String vehicleNumber, String status);
}
