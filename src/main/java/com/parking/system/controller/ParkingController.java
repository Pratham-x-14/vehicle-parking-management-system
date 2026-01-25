package com.parking.system.controller;

import com.parking.system.dto.*;
import com.parking.system.entity.ParkingTicket;
import com.parking.system.service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/park")
    public ResponseEntity<ParkResponse> parkVehicle(@Valid @RequestBody ParkRequest request) {
        return ResponseEntity.ok(parkingService.parkVehicle(request));
    }

    @PostMapping("/unpark")
    public ResponseEntity<UnparkResponse> unparkVehicle(@Valid @RequestBody UnparkRequest request) {
        return ResponseEntity.ok(parkingService.unparkVehicle(request));
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<SlotDTO>> getAvailableSlots() {
        return ResponseEntity.ok(parkingService.getAvailableSlots());
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> bookSlot(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.ok(parkingService.bookSlot(request));
    }

    @GetMapping("/ticket-history")
    public ResponseEntity<List<ParkingTicket>> getHistory(@RequestParam String vehicleNumber) {
        return ResponseEntity.ok(parkingService.getTicketHistory(vehicleNumber));
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<String> updateVehicle(
            @PathVariable Long id,
            @RequestBody UpdateVehicleRequest request) {

        parkingService.updateVehicle(id, request);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        parkingService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/history")
    public ResponseEntity<List<ParkingTicketDTO>> getParkingHistory() {
        return ResponseEntity.ok(parkingService.getAllTickets());
    }

}
