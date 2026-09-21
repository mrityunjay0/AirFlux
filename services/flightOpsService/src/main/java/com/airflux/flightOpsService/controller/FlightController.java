package com.airflux.flightOpsService.controller;

import com.airflux.flightOpsService.service.FlightService;
import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.request.FlightRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.FlightResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@Valid @RequestBody FlightRequest flightRequest,
                                                       @RequestHeader("AirlineId") Long airlineId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightService.createFlight(airlineId, flightRequest));
    }

    @GetMapping("{flightId}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long flightId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightService.getFlightById(flightId));
    }

    @GetMapping("/airline")
    public ResponseEntity<Page<FlightResponse>> getFlightByAirline(@RequestHeader("X-User-Id") Long userId,
                                                                   @RequestParam(required = false) Long departureAirportId,
                                                                   @RequestParam(required = false) Long arrivalAirportId,
                                                                   Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightService.getFlightsByAirline(
                        userId, departureAirportId, arrivalAirportId, pageable
                ));
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightResponse> updateFlight(@PathVariable Long flightId,
                                                       @RequestBody FlightRequest flightRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightService.updateFlight(flightId, flightRequest));
    }

    @PatchMapping("{flightId}/status")
    public ResponseEntity<FlightResponse> changeStatus(@PathVariable Long flightId,
                                                       @RequestParam FlightStatus flightStatus) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightService.changeStatus(flightId, flightStatus));
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<ApiResponse> deleteFlight(@PathVariable Long flightId,
                                                    @RequestHeader("AirlineId") Long airlineId) {

        flightService.deleteFlight(airlineId, flightId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted flight.");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }

}
