package com.parking.system.controller;

import com.parking.system.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/revenue")
    public ResponseEntity<Map<String, Object>> getDailyRevenue() {
        return ResponseEntity.ok(reportService.getDailyRevenue());
    }

    @GetMapping("/occupancy")
    public ResponseEntity<Map<String, Object>> getOccupancy() {
        return ResponseEntity.ok(reportService.getOccupancy());
    }
}
