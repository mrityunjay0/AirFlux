package com.airflux.locationService.controller;

import com.airflux.locationService.service.CityService;
import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.ApiResponse;
import com.airflux.payload.response.CityResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    // CREATE CITY
    @PostMapping("/createCity")
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception {
        CityResponse createdCity = cityService.createCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }


    // GET CITY BY ID
    @GetMapping("/{cityId}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long cityId) throws Exception {
        CityResponse cityResponse = cityService.getCityById(cityId);
        return ResponseEntity.status(HttpStatus.OK).body(cityResponse);
    }


    // GET ALL CITIES (INTO PAGE FORMATE)
    @GetMapping("/getAll")
    public ResponseEntity<Page<CityResponse>> getAllCity(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
            ) throws Exception {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CityResponse> responses = cityService.getAllCities(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }


    // UPDATE CITY
    @PutMapping("/update/{cityId}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long cityId, @Valid @RequestBody CityRequest cityRequest) throws Exception {

        CityResponse updatedCity = cityService.updateCity(cityId, cityRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCity);
    }


    // DELETE CITY
    @DeleteMapping("/delete/{cityId}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long cityId) throws Exception {

        cityService.deleteCity(cityId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse("City deleted successfully"));
    }


    // SEARCH CITIES
    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
            ) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<CityResponse> responses = cityService.searchCitiesByKeyword(keyword,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    // GET CITIES BY COUNTRY CODE
    @GetMapping("/countryCode/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
            @RequestParam String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
            ) throws Exception {

        Pageable pageable = PageRequest.of(page, size);
        Page<CityResponse> responses = cityService.getCitiesByCountryCode(countryCode.toUpperCase(),pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    // CITY EXISTS
    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> existsByCityCode(@PathVariable String cityCode) throws Exception {

        return ResponseEntity.status(HttpStatus.OK)
                .body(cityService.cityExists(cityCode.toUpperCase()));
    }

}
