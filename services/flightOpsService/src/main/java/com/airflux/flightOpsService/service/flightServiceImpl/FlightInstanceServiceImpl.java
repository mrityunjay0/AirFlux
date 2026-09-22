package com.airflux.flightOpsService.service.flightServiceImpl;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.entity.FlightInstance;
import com.airflux.flightOpsService.mapper.FlightInstanceMapper;
import com.airflux.flightOpsService.repository.FlightInstanceRepository;
import com.airflux.flightOpsService.repository.FlightRepository;
import com.airflux.flightOpsService.service.FlightInstanceService;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.AirlineResponse;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightInstanceRepository flightInstanceRepository;
    private final FlightRepository flightRepository;

    public FlightInstanceServiceImpl(FlightInstanceRepository flightInstanceRepository, FlightRepository flightRepository) {
        this.flightInstanceRepository = flightInstanceRepository;
        this.flightRepository = flightRepository;
    }


    @Override
    public FlightInstanceResponse createFlightInstance(FlightInstanceRequest flightInstanceRequest, Long airlineId) {

        // todo : watch airlineId

        Flight flight = flightRepository.findById(flightInstanceRequest.getFlightId()).orElseThrow(
                () -> new ResourceNotFoundException("Flight with given id: "
                        + flightInstanceRequest.getFlightId() + " not found,")
        );

        // Aircraft dummy for now (original after implementing feign client).
        AircraftResponse aircraftResponse = AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();

        FlightInstance flightInstance = FlightInstanceMapper.toEntity(flightInstanceRequest,flight);
        flightInstance.setTotalSeats(aircraftResponse.getTotalSeats());
        flightInstance.setAvailableSeats(aircraftResponse.getTotalSeats());

        FlightInstance savedFlightInstance = flightInstanceRepository.save(flightInstance);

        // todo : create seat instance

        return convertToFlightInstanceResponse(savedFlightInstance);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) {

        FlightInstance flightInstance = flightInstanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Flight Instance with given id: " + id + " not found,")
        );

        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getFlightInstancesByAirlineId(Long airlineId, Long departureAirportId,
                                                                      Long arrivalAirportId, Long flightId,
                                                                      LocalDate onDate, Pageable pageable) {

        LocalDateTime start = onDate != null ? onDate.atStartOfDay() : null;
        LocalDateTime end = onDate != null ? onDate.plusDays(1).atStartOfDay() : null;

        // todo : watch airlineId

        return flightInstanceRepository.findByAirlineId(
                airlineId, departureAirportId, arrivalAirportId, flightId, start, end, pageable
        ).map(this::convertToFlightInstanceResponse);
    }

    @Override
    public FlightInstanceResponse updateFlightInstance(Long flightInstanceId, FlightInstanceRequest flightInstanceRequest) {

        FlightInstance flightInstance = flightInstanceRepository.findById(flightInstanceId).orElseThrow(
                () -> new ResourceNotFoundException("Flight Instance with given id: " + flightInstanceId + " not found,")
        );

        FlightInstanceMapper.updateFlightInstance(flightInstanceRequest, flightInstance);

        return convertToFlightInstanceResponse(flightInstanceRepository.save(flightInstance));
    }

    @Override
    public void deleteFlightInstance(Long flightInstanceId) {

        FlightInstance flightInstance = flightInstanceRepository.findById(flightInstanceId).orElseThrow(
                () -> new ResourceNotFoundException("Flight Instance with id: " + flightInstanceId + " not found,")
        );

        flightInstanceRepository.delete(flightInstance);
    }



    // For FlightInstance entity to response req. parameters (It is subject of inter-service communication)
    // todo: will be implemented in future as during implementation of feign client.
    public FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance) {

        AirlineResponse airlineResponse = AirlineResponse.builder()
                .id(flightInstance.getAirlineId()).build();

        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightInstance.getDepartureAirportId()).build();

        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightInstance.getArrivalAirportId()).build();

        AircraftResponse aircraftResponse = AircraftResponse.builder()
                .id(flightInstance.getFlight().getAircraftId()).build();

        return FlightInstanceMapper.toResponse(
                flightInstance, aircraftResponse,
                airlineResponse, departureAirport,
                arrivalAirport
        );
    }
}
