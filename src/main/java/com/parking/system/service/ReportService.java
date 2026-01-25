package com.parking.system.service;

import java.util.Map;

public interface ReportService {
    Map<String, Object> getDailyRevenue();

    Map<String, Object> getOccupancy();
}
