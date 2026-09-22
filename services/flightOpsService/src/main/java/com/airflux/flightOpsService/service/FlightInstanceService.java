package com.airflux.flightOpsService.service;

import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface FlightInstanceService {

    FlightInstanceResponse createFlightInstance(FlightInstanceRequest flightInstanceRequest,
                                                Long airlineId);

    FlightInstanceResponse getFlightInstanceById(Long id);

    Page<FlightInstanceResponse> getFlightInstancesByAirlineId(Long airlineId, Long departureAirportId,
                                                               Long arrivalAirportId, Long flightId,
                                                               LocalDate onDate, Pageable pageable);

    FlightInstanceResponse updateFlightInstance(Long flightInstanceId, FlightInstanceRequest flightInstanceRequest);

    void deleteFlightInstance(Long flightInstanceId);
}
