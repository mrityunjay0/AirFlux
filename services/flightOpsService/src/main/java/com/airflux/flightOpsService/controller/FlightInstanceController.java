package com.airflux.flightOpsService.controller;

import com.airflux.flightOpsService.service.FlightInstanceService;
import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.FlightInstanceResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/flight-instances")
public class FlightInstanceController {

    private final FlightInstanceService flightInstanceService;

    public FlightInstanceController(FlightInstanceService flightInstanceService) {
        this.flightInstanceService = flightInstanceService;
    }


    @PostMapping
    public ResponseEntity<FlightInstanceResponse> createFlightInstance(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @Valid @RequestBody FlightInstanceRequest flightInstanceRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightInstanceService.createFlightInstance(flightInstanceRequest, airlineId));
    }

    @GetMapping("/{flightInstanceId}")
    public ResponseEntity<FlightInstanceResponse> getFlightInstances(@PathVariable("flightInstanceId") Long flightInstanceId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightInstanceService.getFlightInstanceById(flightInstanceId));
    }

    @GetMapping()
    public ResponseEntity<Page<FlightInstanceResponse>> getFlightInstanceByAirlineId(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long flightId,
            @RequestParam(required = false)LocalDate onDate,
            Pageable pageable
    ) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightInstanceService.getFlightInstancesByAirlineId(
                        airlineId,departureAirportId,arrivalAirportId,flightId,onDate,pageable
                ));
    }

    @PutMapping("/{flightInstanceId}")
    public ResponseEntity<FlightInstanceResponse> updateFlightInstance(
            @PathVariable Long flightInstanceId, @RequestBody FlightInstanceRequest flightInstanceRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightInstanceService.updateFlightInstance(flightInstanceId, flightInstanceRequest));
    }

    @DeleteMapping("/{flightInstanceId}")
    public ResponseEntity<ApiResponse> deleteFlightInstance(@PathVariable Long flightInstanceId) {

        flightInstanceService.deleteFlightInstance(flightInstanceId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted the flight instance");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
