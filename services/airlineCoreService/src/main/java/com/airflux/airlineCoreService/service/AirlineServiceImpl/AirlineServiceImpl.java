package com.airflux.airlineCoreService.service.AirlineServiceImpl;

import com.airflux.airlineCoreService.entity.Airline;
import com.airflux.airlineCoreService.mapper.AirlineMapper;
import com.airflux.airlineCoreService.repository.AirlineRepository;
import com.airflux.airlineCoreService.service.AirlineService;
import com.airflux.payload.enums.AirlineStatus;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.AirlineRequest;
import com.airflux.payload.response.AirlineDropdownItem;
import com.airflux.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public AirlineResponse createAirline(AirlineRequest airlineRequest, Long ownerId) {

        Airline airline = AirlineMapper.toEntity(airlineRequest, ownerId);
        Airline savedAirline = airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with ownerId: " + ownerId + " not found.")
        );

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {

        Airline airline = airlineRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Airline with id: " + id + " not found.")
        );

        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAirlines(Pageable pageable) {

        return airlineRepository.findAll(pageable)
                .map(AirlineMapper::toResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId) {

        Airline airline = airlineRepository.findByOwnerId(ownerId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with ownerId: " +ownerId + " not found.")
        );

        AirlineMapper.updateAirline(airline, airlineRequest);
        Airline savedAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) {

        if(airlineRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Airline with id: " + id + " not found.");
        }
        else if(airlineRepository.findByOwnerId(ownerId).isEmpty()) {
            throw new ResourceNotFoundException("Airline with ownerId: " + ownerId + " not found.");
        }

        airlineRepository.deleteById(id);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus airlineStatus) {

        Airline airline = airlineRepository.findById(airlineId).orElseThrow(
                () -> new ResourceNotFoundException("Airline with id: " + airlineId + " not found.")
        );

        airline.setStatus(airlineStatus);
        Airline savedAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {

        return airlineRepository.findByStatus(AirlineStatus.ACTIVE).stream()
                .map(a -> AirlineDropdownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .iataCode(a.getIataCode())
                        .icaoCode(a.getIcaoCode())
                        .logoUrl(a.getLogoUrl())
                        .build()
                ).toList();
        
    }
}
