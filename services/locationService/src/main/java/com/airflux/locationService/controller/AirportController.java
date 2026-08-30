package com.airflux.locationService.controller;

import com.airflux.locationService.service.AirportServices;
import com.airflux.payload.request.AirportRequest;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportServices airportServices;

    public AirportController(AirportServices airportServices) {
        this.airportServices = airportServices;
    }


    // CREATE AIRPORT
    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest airportRequest) throws Exception {
        AirportResponse createdAirport = airportServices.createAirport(airportRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAirport);
    }


    // GET AIRPORT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception {
        AirportResponse airportResponse = airportServices.getAirportById(id);
        return ResponseEntity.ok(airportResponse);
    }


    // GET ALL AIRPORTS
    @GetMapping
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        List<AirportResponse> airportResponses = airportServices.getAllAirports();
        return ResponseEntity.ok(airportResponses);
    }


    // GET AIRPORTS BY CITY ID
    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<AirportResponse>> getAirportsByCityId(@PathVariable Long cityId) {
        List<AirportResponse> airportResponses = airportServices.getAirportsByCityId(cityId);
        return ResponseEntity.ok(airportResponses);
    }


    // UPDATE AIRPORT
    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id,
            @Valid @RequestBody AirportRequest airportRequest) throws Exception {

        AirportResponse updatedAirport = airportServices.updateAirportById(id, airportRequest);
        return ResponseEntity.ok(updatedAirport);
    }


    // DELETE AIRPORT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
        airportServices.deleteAirportById(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully"));
    }
}