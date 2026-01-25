package com.parking.system.service;

import com.parking.system.dto.*;
import com.parking.system.entity.ParkingTicket;

import java.util.List;

public interface ParkingService {
    ParkResponse parkVehicle(ParkRequest request);

    UnparkResponse unparkVehicle(UnparkRequest request);

    List<SlotDTO> getAvailableSlots();

    BookingResponse bookSlot(BookingRequest request);

    void cancelBooking(Long bookingId);

    List<ParkingTicket> getTicketHistory(String vehicleNumber);

    void updateVehicle(Long vehicleID, UpdateVehicleRequest request);

    void deleteVehicle(Long vehicleID);

    SlotDTO addSlot(AddSlotRequest request);

    void removeSlot(Long slotId);

    List<SlotDTO> getAllSlots();

    List<ParkingTicketDTO> getAllTickets();
}
