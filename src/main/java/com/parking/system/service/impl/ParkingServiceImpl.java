package com.parking.system.service.impl;

import com.parking.system.dto.*;
import com.parking.system.entity.Booking;
import com.parking.system.entity.ParkingSlot;
import com.parking.system.entity.ParkingTicket;
import com.parking.system.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import com.parking.system.enums.VehicleType;
import com.parking.system.exception.SlotNotAvailableException;
import com.parking.system.exception.VehicleAlreadyParkedException;
import com.parking.system.exception.VehicleNotFoundException;
import com.parking.system.repository.BookingRepository;
import com.parking.system.repository.ParkingSlotRepository;
import com.parking.system.repository.ParkingTicketRepository;
import com.parking.system.repository.VehicleRepository;
import com.parking.system.service.ParkingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepository slotRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingTicketRepository ticketRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public ParkResponse parkVehicle(ParkRequest request) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(request.getVehicleNumber())
                .orElseGet(() -> {
                    Vehicle v = new Vehicle();
                    v.setVehicleNumber(request.getVehicleNumber());
                    v.setVehicleType(request.getVehicleType());
                    return vehicleRepository.save(v);
                });

        if (ticketRepository.findByVehicleAndExitTimeIsNull(vehicle).isPresent()) {
            throw new VehicleAlreadyParkedException("Vehicle " + request.getVehicleNumber() + " is already parked.");
        }

        Optional<Booking> bookingOpt = bookingRepository.findByVehicleNumberAndStatus(request.getVehicleNumber(),
                "ACTIVE");
        ParkingSlot slot;

        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            if (booking.getVehicleType() != request.getVehicleType()) {
                throw new IllegalArgumentException("Vehicle type mismatch: Booking is for " + booking.getVehicleType());
            }

            slot = slotRepository.findById(booking.getSlotId())
                    .orElseThrow(() -> new SlotNotAvailableException("Reserved slot not found (system error)"));

            slot.setReserved(false);
            slotRepository.save(slot);

            booking.setStatus("COMPLETED");
            bookingRepository.save(booking);

        } else {
            slot = slotRepository.findFirstBySlotTypeAndIsAvailableTrue(request.getVehicleType())
                    .orElseThrow(() -> new SlotNotAvailableException(
                            "No available slot for type: " + request.getVehicleType()));

            slot.setAvailable(false);
            slotRepository.save(slot);
        }

        ParkingTicket ticket = new ParkingTicket();
        ticket.setVehicle(vehicle);
        ticket.setParkingSlot(slot);
        ticket.setEntryTime(LocalDateTime.now());
        ticketRepository.save(ticket);

        vehicle.setEntryTime(ticket.getEntryTime());
        vehicle.setExitTime(null);
        vehicleRepository.save(vehicle);

        return ParkResponse.builder()
                .ticketId(ticket.getId())
                .slotNumber(slot.getSlotNumber())
                .vehicleNumber(vehicle.getVehicleNumber())
                .entryTime(ticket.getEntryTime())
                .build();
    }

    @Override
    @Transactional
    public UnparkResponse unparkVehicle(UnparkRequest request) {
        String vehicleNumber = request.getVehicleNumber().trim();
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found: " + vehicleNumber));

        ParkingTicket ticket = ticketRepository.findByVehicleAndExitTimeIsNull(vehicle)
                .orElseThrow(
                        () -> new VehicleNotFoundException("No active ticket found for vehicle: " + vehicleNumber));

        ParkingSlot slot = ticket.getParkingSlot();

        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);

        long durationMinutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        double hours = Math.ceil(durationMinutes / 60.0);
        if (hours == 0)
            hours = 1;

        double rate = getRate(slot.getSlotType());
        double amount = hours * rate;

        ticket.setAmount(amount);
        ticketRepository.save(ticket);

        slot.setAvailable(true);
        slot.setReserved(false);
        slotRepository.save(slot);

        vehicle.setExitTime(exitTime);
        vehicleRepository.save(vehicle);

        return UnparkResponse.builder()
                .ticketId(ticket.getId())
                .vehicleNumber(vehicleNumber)
                .entryTime(ticket.getEntryTime())
                .exitTime(exitTime)
                .totalAmount(amount)
                .itemizedAmount(String.format("%.0f hours @ %.0f/hr", hours, rate))
                .build();
    }

    @Override
    @Transactional
    public BookingResponse bookSlot(BookingRequest request) {
        if (bookingRepository.findByVehicleNumberAndStatus(request.getVehicleNumber(), "ACTIVE").isPresent()) {
            throw new RuntimeException("Vehicle already has active booking");
        }

        ParkingSlot slot = slotRepository.findFirstBySlotTypeAndIsAvailableTrue(request.getVehicleType())
                .orElseThrow(() -> new SlotNotAvailableException("No available slot to book"));

        slot.setAvailable(false);
        slot.setReserved(true);
        slotRepository.save(slot);

        Booking booking = new Booking();
        booking.setVehicleNumber(request.getVehicleNumber());
        booking.setVehicleType(request.getVehicleType());
        booking.setBookingTime(LocalDateTime.now());
        booking.setValidUntil(LocalDateTime.now().plusHours(24));
        booking.setStatus("ACTIVE");
        booking.setSlotId(slot.getId());

        bookingRepository.save(booking);

        return BookingResponse.builder()
                .bookingId(booking.getId())
                .vehicleNumber(booking.getVehicleNumber())
                .slotNumber(slot.getSlotNumber())
                .bookingTime(booking.getBookingTime())
                .validUntil(booking.getValidUntil())
                .status(booking.getStatus())
                .build();
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!"ACTIVE".equals(booking.getStatus())) {
            throw new RuntimeException("Booking is not active");
        }

        ParkingSlot slot = slotRepository.findById(booking.getSlotId()).orElse(null);
        if (slot != null) {
            slot.setAvailable(true);
            slot.setReserved(false);
            slotRepository.save(slot);
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }

    @Override
    public List<ParkingTicket> getTicketHistory(String vehicleNumber) {
        Vehicle vehicle = vehicleRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        return ticketRepository.findByVehicle(vehicle);
    }

    @Override
    public List<SlotDTO> getAvailableSlots() {
        return slotRepository.findByIsAvailableTrue().stream()
                .map(slot -> SlotDTO.builder()
                        .slotNumber(slot.getSlotNumber())
                        .type(slot.getSlotType())
                        .available(slot.isAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    private double getRate(VehicleType type) {
        return switch (type) {
            case BIKE -> 10.0;
            case CAR -> 20.0;
            case TRUCK -> 30.0;
        };
    }

    @Override
    @Transactional
    public void updateVehicle(Long vehicleID, UpdateVehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(vehicleID)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());

        vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void deleteVehicle(Long vehicleID) {
        if (!vehicleRepository.existsById(vehicleID)) {
            throw new VehicleNotFoundException("Vehicle not found with ID: " + vehicleID);
        }
        vehicleRepository.deleteById(vehicleID);
    }

    @Override
    public List<SlotDTO> getAllSlots() {
        return slotRepository.findAll().stream()
                .map(slot -> SlotDTO.builder()
                        .slotNumber(slot.getSlotNumber())
                        .type(slot.getSlotType())
                        .available(slot.isAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingTicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticket -> new ParkingTicketDTO(
                        ticket.getId(),
                        ticket.getVehicle().getVehicleNumber(),
                        ticket.getParkingSlot().getSlotNumber(),
                        ticket.getEntryTime(),
                        ticket.getExitTime(),
                        ticket.getAmount()))
                .collect(Collectors.toList());
    }

    @Override
    public SlotDTO addSlot(AddSlotRequest request) {
        if (slotRepository.existsBySlotNumber(request.getSlotNumber())) {
            throw new IllegalArgumentException("Slot with number " + request.getSlotNumber() + " already exists");
        }

        ParkingSlot slot = new ParkingSlot();
        slot.setSlotNumber(request.getSlotNumber());
        slot.setSlotType(request.getType());
        slot.setAvailable(true);
        slot.setReserved(false);

        ParkingSlot savedSlot = slotRepository.save(slot);

        return SlotDTO.builder()
                .slotNumber(savedSlot.getSlotNumber())
                .type(savedSlot.getSlotType())
                .available(savedSlot.isAvailable())
                .build();
    }

    @Override
    public void removeSlot(Long slotId) {
        if (!slotRepository.existsById(slotId)) {
            throw new RuntimeException("Slot found with ID: " + slotId);
        }

        // Check if slot has dependent data (tickets/bookings) before determining how to
        // delete
        // For now, blocking deletion if used to prevent FK violations
        // Ideally, we might want to soft-delete or cascade if business logic permits
        // Assuming simple protection for now:
        try {
            slotRepository.deleteById(slotId);
        } catch (Exception e) {
            throw new RuntimeException("Cannot remove slot. It may be in use by tickets or bookings.", e);
        }
    }
}