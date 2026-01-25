package com.parking.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Vehicle Parking System API is running. Access frontend at http://localhost:5173";
    }
}
