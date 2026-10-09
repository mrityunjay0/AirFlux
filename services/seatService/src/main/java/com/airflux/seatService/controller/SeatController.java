package com.airflux.seatService.controller;

import com.airflux.payload.request.SeatRequest;
import com.airflux.payload.response.SeatResponse;
import com.airflux.seatService.service.SeatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }


    @GetMapping
    public ResponseEntity<List<SeatResponse>> getAllSeats() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(seatService.getAllSeats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(seatService.getSeatById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponse> updateSeat(@PathVariable Long id,
                                                   @Valid @RequestBody SeatRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(seatService.updateSeat(id, request));
    }
}
