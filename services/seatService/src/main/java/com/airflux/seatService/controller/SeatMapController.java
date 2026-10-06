package com.airflux.seatService.controller;

import com.airflux.payload.request.SeatMapRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.SeatMapResponse;
import com.airflux.seatService.service.SeatMapService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seat-maps")
public class SeatMapController {

    private final SeatMapService seatMapService;

    public SeatMapController(SeatMapService seatMapService) {
        this.seatMapService = seatMapService;
    }


    @PostMapping("/{airlineId}")
    public ResponseEntity<SeatMapResponse> createSeatMap(@PathVariable Long airlineId,
                                                         @Valid @RequestBody SeatMapRequest seatMapRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seatMapService.createSeatMap(airlineId, seatMapRequest));
    }

    @GetMapping("/{seatMapId}")
    public ResponseEntity<SeatMapResponse> getSeatMap(@PathVariable Long seatMapId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(seatMapService.getSeatMapById(seatMapId));
    }

    @GetMapping("/{cabinClassId}")
    public ResponseEntity<SeatMapResponse> getSeatMapByCabin(@PathVariable Long cabinClassId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(seatMapService.getSeatMapByCabinClass(cabinClassId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatMapResponse> updateSeatMap(@PathVariable Long id,
                                                         @Valid @RequestBody SeatMapRequest seatMapRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(seatMapService.updateSeatMap(id, seatMapRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSeatMap(@PathVariable Long id) {

        seatMapService.deleteSeatMap(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("SeatMap deleted successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
