package com.airflux.pricingService.controller;

import com.airflux.payload.request.BaggagePolicyRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.BaggagePolicyResponse;
import com.airflux.pricingService.service.BaggagePolicyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/baggage-policies")
public class BaggagePolicyController {

    private final BaggagePolicyService baggagePolicyService;

    public BaggagePolicyController(BaggagePolicyService baggagePolicyService) {
        this.baggagePolicyService = baggagePolicyService;
    }


    @PostMapping
    public ResponseEntity<BaggagePolicyResponse> createBaggagePolicy(@Valid @RequestBody BaggagePolicyRequest baggagePolicyRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(baggagePolicyService.createBaggagePolicy(baggagePolicyRequest));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BaggagePolicyResponse> getBaggagePolicy(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(baggagePolicyService.getBaggagePolicyById(id));
    }

    @GetMapping("/{fairId}")
    public  ResponseEntity<BaggagePolicyResponse> getBaggagePolicyByFairId(@PathVariable Long fairId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(baggagePolicyService.getBaggagePolicyByFareId(fairId));
    }

    @GetMapping("/{airlineId}")
    public  ResponseEntity<List<BaggagePolicyResponse>> getBaggagePolicyByAirlineId(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(baggagePolicyService.getBaggagePolicyByAirlineId(airlineId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaggagePolicyResponse> updateBaggagePolicy(@PathVariable Long id,
                                                                     @Valid @RequestBody BaggagePolicyRequest baggagePolicyRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(baggagePolicyService.updateBaggagePolicy(id, baggagePolicyRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBaggagePolicy(@PathVariable Long id) {

        baggagePolicyService.deleteBaggagePolicy(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deleted successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
