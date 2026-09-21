package com.airflux.flightOpsService.service.flightServiceImpl;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.mapper.FlightMapper;
import com.airflux.flightOpsService.repository.FlightRepository;
import com.airflux.flightOpsService.service.FlightService;
import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.request.FlightRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.AirlineResponse;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) {

        if(flightRepository.existsByFlightNumber(flightRequest.getFlightNumber())) {
            throw new DuplicateResourceException("Flight with given number: "
                    + flightRequest.getFlightNumber() + " already exists.");
        }

        Flight flight = FlightMapper.toEntity(flightRequest);

        Flight savedFlight = flightRepository.save(flight);

        return convertToFlightResponse(savedFlight);


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


    // For Flight entity to response req. parameters (It is subject of inter-service communication)
    // will be implemented in future as during implementation of feign client.
    public FlightResponse convertToFlightResponse(Flight flight) {

        AircraftResponse aircraftResponse = AircraftResponse.builder()
                .id(flight.getAircraftId())
                .build();
        AirlineResponse airlineResponse = AirlineResponse.builder()
                .id(flight.getAirlineId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();

        return FlightMapper.toResponse(flight,aircraftResponse,airlineResponse,
                departureAirport,arrivalAirport);
    }
}
