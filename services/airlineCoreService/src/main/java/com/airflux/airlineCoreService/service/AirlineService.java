package com.airflux.airlineCoreService.service;

import com.airflux.payload.enums.AirlineStatus;
import com.airflux.payload.request.AirlineRequest;
import com.airflux.payload.response.AirlineDropdownItem;
import com.airflux.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest airlineRequest, Long ownerId);

    AirlineResponse getAirlineByOwner(Long ownerId);
    AirlineResponse getAirlineById(Long id);
    Page<AirlineResponse> getAirlines(Pageable pageable);

    AirlineResponse updateAirline(AirlineRequest airlineRequest, Long ownerId);

    void deleteAirline(Long id, Long ownerId);

    AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus airlineStatus);

    List<AirlineDropdownItem> getAirlineDropdown();

}
