package com.airflux.airlineCoreService.service;

import com.airflux.payload.request.AircraftRequest;
import com.airflux.payload.response.AircraftResponse;

import java.util.List;

public interface AircraftService {

    AircraftResponse createAircraft(AircraftRequest aircraftRequest, Long ownerId);

    AircraftResponse getAircraftById(Long id);
    List<AircraftResponse> getAircraftByOwnerId(Long ownerId);

    AircraftResponse updateAircraft(Long id, AircraftRequest aircraftRequest, Long ownerId);

    void deleteAircraft(Long id, Long ownerId);
}
