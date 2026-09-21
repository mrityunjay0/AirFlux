package com.airflux.flightOpsService.service.flightServiceImpl;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.entity.FlightInstance;
import com.airflux.flightOpsService.mapper.FlightInstanceMapper;
import com.airflux.flightOpsService.mapper.FlightMapper;
import com.airflux.flightOpsService.repository.FlightInstanceRepository;
import com.airflux.flightOpsService.repository.FlightRepository;
import com.airflux.flightOpsService.service.FlightInstanceService;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        return FlightMapper.toResponse(savedFlightInstance);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) {
        return null;
    }

    @Override
    public Page<FlightInstanceResponse> getFlightInstancesByAirlineId(Long airlineId, Long departureAirportId, Long arrivalAirportId, Long flightId, Long onDate, Pageable pageable) {
        return null;
    }

    @Override
    public FlightInstanceResponse updateFlightInstance(Long flightInstanceId, FlightInstanceRequest flightInstanceRequest) {
        return null;
    }

    @Override
    public void deleteFlightInstance(Long flightInstanceId) {

    }
}
