package com.airflux.locationService.service.impl;

import com.airflux.locationService.entity.Airport;
import com.airflux.locationService.entity.City;
import com.airflux.locationService.mapper.AirportMapper;
import com.airflux.locationService.repository.AirportRepository;
import com.airflux.locationService.repository.CityRepository;
import com.airflux.locationService.service.AirportServices;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
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
    public AirportResponse createAirport(AirportRequest airportRequest) {

        if(airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()){
            throw new DuplicateResourceException("Airport with IATA code "
                    + airportRequest.getIataCode() + " already exists.");
        }

        City city = cityRepository.findById(airportRequest.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("City with id "
                        + airportRequest.getCityId() + " not found."));


        Airport airport = AirportMapper.toEntity(airportRequest);
        airport.setCity(city);
        Airport createdAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(createdAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) {

        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Airport with given ID does not exists.")
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

        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public AirportResponse updateAirportById(Long id, AirportRequest airportRequest) {

        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Airport with given id does not exists.")
        );

        if(airportRequest.getIataCode() != null &&
            !airportRequest.getIataCode().equals(airport.getIataCode())
            && airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()){
                throw new DuplicateResourceException("Airport with given IATA Code already exists.");
        }

        Airport updatedAirport = airportRepository
                .save(AirportMapper.updateEntity(airport, airportRequest));

        return AirportMapper.toResponse(updatedAirport);

    }

    @Override
    public void deleteAirportById(Long id) {

        Airport airport = airportRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Airport not found.")
        );

        airportRepository.delete(airport);
    }
}
