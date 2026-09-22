package com.airflux.flightOpsService.controller;

import com.airflux.flightOpsService.service.FlightInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flight-instances")
public class FlightInstanceController {

    private final FlightInstanceService flightInstanceService;

    public FlightInstanceController(FlightInstanceService flightInstanceService) {
        this.flightInstanceService = flightInstanceService;
    }

    
}
