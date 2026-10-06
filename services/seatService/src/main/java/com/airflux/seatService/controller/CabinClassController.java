package com.airflux.seatService.controller;

import com.airflux.seatService.service.CabinClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cabin-classes")
public class CabinClassController {

    private final CabinClassService cabinClassService;

    public CabinClassController(CabinClassService cabinClassService) {
        this.cabinClassService = cabinClassService;
    }

    
}
