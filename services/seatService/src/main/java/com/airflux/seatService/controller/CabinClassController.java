package com.airflux.seatService.controller;

import com.airflux.payload.enums.CabinClassType;
import com.airflux.payload.request.CabinClassRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.CabinClassResponse;
import com.airflux.seatService.service.CabinClassService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cabin-classes")
public class CabinClassController {

    private final CabinClassService cabinClassService;

    public CabinClassController(CabinClassService cabinClassService) {
        this.cabinClassService = cabinClassService;
    }


    @PostMapping
    public ResponseEntity<CabinClassResponse> createCabinClass(@Valid @RequestBody CabinClassRequest cabinClassRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cabinClassService.createCabinClass(cabinClassRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinClassResponse> getCabinClassById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cabinClassService.getCabinClassById(id));
    }

    @GetMapping("/{aircraftId}")
    public ResponseEntity<List<CabinClassResponse>> getCabinClassesByAirportId(@PathVariable Long aircraftId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cabinClassService.getCabinClassesByAircraftId(aircraftId));
    }

    @GetMapping("/aircraft/{aircraftId}/type/{type}")
    public ResponseEntity<CabinClassResponse> getCabinClassByAircraftIdAndType(@PathVariable Long aircraftId,
                                                                               @PathVariable CabinClassType type) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cabinClassService.getCabinClassByAircraftIdAndType(aircraftId, type));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinClassResponse> updateCabinClass(@PathVariable Long id,
                                                               @Valid @RequestBody CabinClassRequest cabinClassRequest) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cabinClassService.updateCabinClass(id, cabinClassRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCabinClassById(@PathVariable Long id) {

        cabinClassService.deleteCabinClassById(id);

        ApiResponse response = new ApiResponse();
        response.setMessage("Cabin class deleted successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
