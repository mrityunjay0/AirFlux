package com.airflux.flightOpsService.service.flightServiceImpl;

import com.airflux.flightOpsService.service.FlightService;
import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.request.FlightRequest;
import com.airflux.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) {


    }

    @Override
    public Page<FlightResponse> getFlightsByAirline(Long airlineId, Long departureAirportId, Long arrivalAirportId, Pageable pageable) {
        return null;
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        return null;
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        return null;
    }

    @Override
    public FlightResponse changeStatus(Long id, FlightStatus flightStatus) {
        return null;
    }

    @Override
    public void deleteFlight(Long id) {

    }
}
