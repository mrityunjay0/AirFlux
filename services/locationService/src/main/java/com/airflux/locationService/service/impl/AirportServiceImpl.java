package com.airflux.locationService.service.impl;

import com.airflux.locationService.repository.AirportRepository;
import com.airflux.locationService.service.AirportServices;
import com.airflux.payload.request.AirportRequest;
import com.airflux.payload.response.AirportResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportServices {

    private final AirportRepository airportRepository;
    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        return null;
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        return null;
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return List.of();
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return List.of();
    }

    @Override
    public AirportResponse updateAirportById(Long id, AirportRequest airportRequest) {
        return null;
    }

    @Override
    public void deleteAirportById(Long id) {

    }
}
