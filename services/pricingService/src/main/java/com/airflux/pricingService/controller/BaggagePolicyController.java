package com.airflux.pricingService.controller;

import com.airflux.pricingService.service.BaggagePolicyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/baggage-policies")
public class BaggagePolicyController {

    private final BaggagePolicyService baggagePolicyService;

    public BaggagePolicyController(BaggagePolicyService baggagePolicyService) {
        this.baggagePolicyService = baggagePolicyService;
    }


    
}
