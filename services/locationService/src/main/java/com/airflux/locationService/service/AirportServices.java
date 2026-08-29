package com.airflux.locationService.service;

import com.airflux.payload.request.AirportRequest;
import com.airflux.payload.response.AirportResponse;

import java.util.List;

public interface AirportServices {

    AirportResponse createAirport(AirportRequest airportRequest);

    AirportResponse getAirportById(Long id);
    List<AirportResponse> getAllAirports();
    List<AirportResponse> getAirportsByCityId(Long cityId);

    AirportResponse updateAirportById(Long id, AirportRequest airportRequest);

    void deleteAirportById(Long id);

}
