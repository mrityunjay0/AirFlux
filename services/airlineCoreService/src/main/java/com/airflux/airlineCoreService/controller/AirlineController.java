package com.airflux.airlineCoreService.controller;

import com.airflux.airlineCoreService.service.AirlineService;
import com.airflux.payload.enums.AirlineStatus;
import com.airflux.payload.request.AirlineRequest;
import com.airflux.payload.response.AirlineDropdownItem;
import com.airflux.payload.response.AirlineResponse;
import com.airflux.payload.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }


    // CREATE AIRLINE
    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(@Valid @RequestBody AirlineRequest airlineRequest,
                                                         @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(airlineService.createAirline(airlineRequest, userId)
        );
    }


    // GET AIRLINE BY OWNER ID
    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwnerId(@RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.getAirlineByOwner(userId)
        );
    }


    // GET AIRLINE BY ID
    @GetMapping("/{airlineId}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.getAirlineById(airlineId));
    }


    // GET ALL AIRLINES
    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAirlines(Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.getAirlines(pageable));
    }


    // GET AIRLINE DROPDOWN
    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropdownItem>> getDropdown() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.getAirlineDropdown());
    }


    // UPDATE AIRLINE BY AIRLINE ID AND OWNER ID
    @PutMapping
    public ResponseEntity<AirlineResponse> updateAirline(@Valid @RequestBody AirlineRequest airlineRequest,
                                                         @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.updateAirline(airlineRequest, userId));
    }


    // DELETE AIRLINE BY ID
    @DeleteMapping("{airlineId}")
    public ResponseEntity<ApiResponse> deleteAirline(@PathVariable Long airlineId, @RequestHeader("X-User-Id") Long userId) {

        airlineService.deleteAirline(airlineId, userId);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Airline deleted successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }


    // CHANGE STATUS BY ADMIN
    @PostMapping("/{airlineId}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.changeStatusByAdmin(airlineId, AirlineStatus.ACTIVE));
    }

    @PostMapping("/{airlineId}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.changeStatusByAdmin(airlineId, AirlineStatus.INACTIVE));
    }

    @PostMapping("/{airlineId}/ban")
    public ResponseEntity<AirlineResponse> banAirline(@PathVariable Long airlineId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(airlineService.changeStatusByAdmin(airlineId, AirlineStatus.BANNED));
    }
}
