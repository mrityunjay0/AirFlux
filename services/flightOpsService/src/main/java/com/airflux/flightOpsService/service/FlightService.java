package com.airflux.flightOpsService.service;

import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.request.FlightRequest;
import com.airflux.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {

    FlightResponse createFlight(Long airlineId, FlightRequest flightRequest);

    Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId,
                                             Long arrivalAirportId,Pageable pageable);
    FlightResponse getFlightById(Long id);

    FlightResponse updateFlight(Long id, FlightRequest flightRequest);

    FlightResponse changeStatus(Long id, FlightStatus flightStatus);

    void deleteFlight(Long airlineId, Long id);
}
