package com.airflux.pricingService.controller;

import com.airflux.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @GetMapping
    public ApiResponse home() {
        return new ApiResponse("This is Pricing service home.");
    }
}
