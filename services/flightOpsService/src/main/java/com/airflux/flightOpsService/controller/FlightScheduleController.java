package com.airflux.flightOpsService.controller;

import com.airflux.flightOpsService.service.FlightScheduleService;
import com.airflux.payload.request.FlightScheduleRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.FlightScheduleResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flight-schedules")
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleController(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }


    @PostMapping
    public ResponseEntity<FlightScheduleResponse> createFlightSchedule(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @Valid @RequestBody FlightScheduleRequest flightScheduleRequest) {

//        todo: watch airlineId
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightScheduleService.createFlightSchedule(airlineId, flightScheduleRequest));
    }

    @GetMapping("/{airlineId}")
    public ResponseEntity<?> getFlightScheduleByAirlineId(@RequestHeader("X-Airline-Id") Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightScheduleService.getFlightScheduleByAirline(airlineId));
    }

    @GetMapping("/{flightScheduleId}")
    public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(@PathVariable("flightScheduleId") Long flightScheduleId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightScheduleService.getFlightScheduleById(flightScheduleId));
    }

    @PutMapping("/{flightScheduleId}")
    public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(@PathVariable Long flightScheduleId,
                                                                       @Valid @RequestBody FlightScheduleRequest flightScheduleRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(flightScheduleService.updateFlightSchedule(flightScheduleId, flightScheduleRequest));
    }

    @DeleteMapping("/{flightScheduleId}")
    public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long flightScheduleId) {

        flightScheduleService.deleteFlightScheduleById(flightScheduleId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted FlightSchedule");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
