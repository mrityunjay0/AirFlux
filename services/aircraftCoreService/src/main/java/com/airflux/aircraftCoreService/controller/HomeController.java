package com.airflux.aircraftCoreService.controller;

import com.airflux.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {

    @GetMapping
    public ApiResponse apiResponse() {
        return new ApiResponse("Aircraft Core Service is up and running");
    }
}
