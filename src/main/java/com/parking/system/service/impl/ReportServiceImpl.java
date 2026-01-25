package com.parking.system.service.impl;

import com.parking.system.entity.ParkingTicket;
import com.parking.system.repository.ParkingSlotRepository;
import com.parking.system.repository.ParkingTicketRepository;
import com.parking.system.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ParkingTicketRepository ticketRepository;
    private final ParkingSlotRepository slotRepository;

    public ReportServiceImpl(ParkingTicketRepository ticketRepository, ParkingSlotRepository slotRepository) {
        this.ticketRepository = ticketRepository;
        this.slotRepository = slotRepository;
    }

    @Override
    public Map<String, Object> getDailyRevenue() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);

        // Fetch all tickets exited today
        List<ParkingTicket> tickets = ticketRepository.findAll().stream()
                .filter(t -> t.getExitTime() != null &&
                        t.getExitTime().isAfter(startOfDay) &&
                        t.getExitTime().isBefore(endOfDay))
                .collect(Collectors.toList());

        double totalRevenue = tickets.stream()
                .mapToDouble(t -> t.getAmount() != null ? t.getAmount() : 0.0)
                .sum();

        Map<String, Object> response = new HashMap<>();
        response.put("date", LocalDate.now().toString());
        response.put("totalRevenue", totalRevenue);
        response.put("ticketsCount", tickets.size());

        return response;
    }

    @Override
    public Map<String, Object> getOccupancy() {
        long totalSlots = slotRepository.count();
        long availableSlots = slotRepository.findByIsAvailableTrue().size();
        long occupiedSlots = totalSlots - availableSlots;

        Map<String, Object> response = new HashMap<>();
        response.put("totalSlots", totalSlots);
        response.put("occupiedSlots", occupiedSlots);
        response.put("availableSlots", availableSlots);

        return response;
    }
}
