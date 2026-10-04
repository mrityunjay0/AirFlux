package com.airflux.pricingService.controller;

import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.FareResponse;
import com.airflux.pricingService.service.FareService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/fares")
public class FareController {

    private final FareService fareService;

    public FareController(FareService fareService) {
        this.fareService = fareService;
    }


    @PostMapping
    public ResponseEntity<FareResponse> createFare(@Valid @RequestBody FareRequest fareRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fareService.createFare(fareRequest));
    }

    @GetMapping("/{fareId}")
    public ResponseEntity<FareResponse> getFareById(@PathVariable Long fareId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.getFareById(fareId));
    }

    @GetMapping("/flight/{flightId}/cabin-Class/{cabinClassId}")
    public ResponseEntity<List<FareResponse>> getFaresByFlightIdAndCabinClassId(
            @PathVariable Long flightId,
            @PathVariable Long cabinClassId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.getFaresByFlightIdAndCabinClassId(flightId, cabinClassId));
    }

    @PutMapping("/{fareId}")
    public ResponseEntity<FareResponse> updateFare(@PathVariable Long fareId,
                                                   @Valid @RequestBody FareRequest fareRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.updateFare(fareId, fareRequest));
    }

    @DeleteMapping("/{fareId}")
    public ResponseEntity<ApiResponse> deleteFareById(@PathVariable Long fareId) {

        fareService.deleteFareById(fareId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted fare");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }

    @PostMapping("/batch-by-flight-ids")
    public ResponseEntity<Map<Long, FareResponse>> getFaresByIds(@RequestBody List<Long> flightIds) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.getFaresById(flightIds));
    }

    @PostMapping("/search-lowest-fares")
    public ResponseEntity<Map<Long, FareResponse>> getLowestFaresPerFlight(@RequestBody List<Long> flightIds,
                                                                           @RequestBody Long cabinClassId
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.getLowestFaresPerFlight(flightIds, cabinClassId));
    }
}
