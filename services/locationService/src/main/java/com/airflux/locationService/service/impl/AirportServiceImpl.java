package com.airflux.locationService.service.impl;

import com.airflux.locationService.entity.Airport;
import com.airflux.locationService.entity.City;
import com.airflux.locationService.mapper.AirportMapper;
import com.airflux.locationService.repository.AirportRepository;
import com.airflux.locationService.repository.CityRepository;
import com.airflux.locationService.service.AirportServices;
import com.airflux.payload.request.AirportRequest;
import com.airflux.payload.response.AirportResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportServices {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;
    public AirportServiceImpl(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) throws Exception {

        if(airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()){
            throw new Exception("Airport with given IATA Code already exists.");
        }

        City city = cityRepository.findById(airportRequest.getCityId())
                .orElseThrow(() -> new Exception("City with id " + airportRequest.getCityId() + " not found.")
                );

        Airport airport = AirportMapper.toEntity(airportRequest);
        airport.setCity(city);
        Airport createdAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(createdAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {

        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new Exception("Airport with given ID does not exists.")
        );

        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {

        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return List.of();
    }

    @Override
    public AirportResponse updateAirportById(Long id, AirportRequest airportRequest) throws Exception {

        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new Exception("Airport with given id does not exists.")
        );

        
    }

    @Override
    public void deleteAirportById(Long id) {

    }
}
