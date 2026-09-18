package com.airflux.airlineCoreService.service.AirlineServiceImpl;

import com.airflux.airlineCoreService.entity.Aircraft;
import com.airflux.airlineCoreService.entity.Airline;
import com.airflux.airlineCoreService.mapper.AircraftMapper;
import com.airflux.airlineCoreService.repository.AircraftRepository;
import com.airflux.airlineCoreService.repository.AirlineRepository;
import com.airflux.airlineCoreService.service.AircraftService;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.AircraftRequest;
import com.airflux.payload.response.AircraftResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, AirlineRepository airlineRepository) {
        this.aircraftRepository = aircraftRepository;
        this.airlineRepository = airlineRepository;
    }


    @Override
    public AircraftResponse createAircraft(AircraftRequest aircraftRequest, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with ownerId: " + ownerId + " not found")
        );

        Aircraft aircraft = AircraftMapper.toEntity(aircraftRequest, airline);

        if(aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new DuplicateResourceException("Aircraft with code: " + aircraft.getCode() + " already exists");
        }

        if(aircraft.getSeatingCapacity() < aircraft.getTotalSeats()) {
            throw new IllegalArgumentException("Seating capacity exceeded");
        }

        return AircraftMapper.toResponse(aircraftRepository.save(aircraft));
    }

    @Override
    public AircraftResponse getAircraftById(Long id) {

        Aircraft aircraft = aircraftRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Aircraft with id: " + id + " not found")
        );

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> getAircraftByOwnerId(Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with ownerId: " + ownerId + " not found")
        );

        return aircraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AircraftMapper::toResponse).toList();
    }

    @Override
    public AircraftResponse updateAircraft(Long id, AircraftRequest aircraftRequest, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with ownerId: " + ownerId + " not found")
        );

        Aircraft aircraft = aircraftRepository.findByIdAndAirlineId(id, airline.getId());

        if(aircraft == null) {
            throw new ResourceNotFoundException("Aircraft with id: " + id + " not found");
        }

        if(aircraftRepository.existsByCode(aircraft.getCode())) {
            throw new DuplicateResourceException("Aircraft with code: " + aircraft.getCode() + " already exists");
        }

    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) {

    }
}
