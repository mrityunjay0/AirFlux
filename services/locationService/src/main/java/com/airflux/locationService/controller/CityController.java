package com.airflux.locationService.controller;

import com.airflux.locationService.service.CityService;
import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/createCity")
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception {
        CityResponse createdCity = cityService.createCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long cityId) throws Exception {
        CityResponse cityResponse = cityService.getCityById(cityId);
        return ResponseEntity.status(HttpStatus.OK).body(cityResponse);
    }

    
}
