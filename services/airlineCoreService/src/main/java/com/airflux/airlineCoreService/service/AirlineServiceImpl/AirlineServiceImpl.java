package com.airflux.airlineCoreService.service.AirlineServiceImpl;

import com.airflux.airlineCoreService.entity.Airline;
import com.airflux.airlineCoreService.mapper.AirlineMapper;
import com.airflux.airlineCoreService.repository.AirlineRepository;
import com.airflux.airlineCoreService.service.AirlineService;
import com.airflux.payload.enums.AirlineStatus;
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
        return null;
    }

    @Override
    public AirlineResponse getAirlineById(Long id) {
        return null;
    }

    @Override
    public Page<AirlineResponse> getAirlines(Pageable pageable) {
        return null;
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId) {
        return null;
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) {

    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus airlineStatus) {
        return null;
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown() {
        return List.of();
    }
}
