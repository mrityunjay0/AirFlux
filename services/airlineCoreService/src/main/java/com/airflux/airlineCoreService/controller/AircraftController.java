package com.airflux.airlineCoreService.controller;

import com.airflux.airlineCoreService.service.AircraftService;
import com.airflux.payload.request.AircraftRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }


    // CREATE AIRCRAFT
    @PostMapping
    public ResponseEntity<AircraftResponse> createAircraft(@Valid @RequestBody AircraftRequest aircraftRequest,
                                                           @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aircraftService.createAircraft(aircraftRequest, userId));
    }


    // GET AIRCRAFT BY ID
    @GetMapping("/{aircraftId}")
    public ResponseEntity<AircraftResponse> getAircraftById(@RequestParam Long aircraftId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(aircraftService.getAircraftById(aircraftId));
    }


    // GET AIRCRAFTS LIST
    @GetMapping
    public ResponseEntity<List<AircraftResponse>> getAllAircrafts(@RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(aircraftService.getAircraftByOwnerId(userId));
    }


    // UPDATE AIRCRAFT
    @PutMapping("/{aircraftId}")
    public ResponseEntity<AircraftResponse> updateAircraft(@RequestParam Long aircraftId,
                                                           @RequestBody AircraftRequest aircraftRequest,
                                                           @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(aircraftService.updateAircraft(aircraftId, aircraftRequest, userId));
    }


    // DELETE AIRCRAFT
    @DeleteMapping("/{aircraftId}")
    public ResponseEntity<AircraftResponse> deleteAircraft(@RequestParam Long aircraftId,
                                                           @RequestHeader("X-User-Id") Long userId) {

        aircraftService.deleteAircraft(aircraftId, userId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully deleted aircraft");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }

}
