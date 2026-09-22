package com.airflux.flightOpsService.service.flightServiceImpl;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.entity.FlightSchedule;
import com.airflux.flightOpsService.mapper.FlightScheduleMapper;
import com.airflux.flightOpsService.repository.FlightRepository;
import com.airflux.flightOpsService.repository.FlightScheduleRepository;
import com.airflux.flightOpsService.service.FlightInstanceService;
import com.airflux.flightOpsService.service.FlightScheduleService;
import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.request.FlightScheduleRequest;
import com.airflux.payload.response.FlightScheduleResponse;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightRepository flightRepository;
    private final FlightInstanceService flightInstanceService;

    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository, FlightRepository flightRepository, FlightInstanceService flightInstanceService) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.flightRepository = flightRepository;
        this.flightInstanceService = flightInstanceService;
    }


    @Override
    public FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest flightScheduleRequest) {

        Flight flight = flightRepository.findById(flightScheduleRequest.getFlightId()).orElseThrow(
                () -> new ResourceNotFoundException("Flight with given id: " +
                        flightScheduleRequest.getFlightId() + " not found.")
        );

        if (flightScheduleRequest.getEndDate().isBefore(flightScheduleRequest.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        FlightSchedule flightSchedule = FlightScheduleMapper.toEntity(flightScheduleRequest, flight);

        FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);

        // create flight instance for range of dates
        List<DayOfWeek> operatingDays = savedFlightSchedule.getOperatingDays();
        LocalDate startDate = savedFlightSchedule.getStartDate();
        LocalDate endDate = savedFlightSchedule.getEndDate();

        FlightInstanceRequest flightInstanceRequest = FlightInstanceRequest.builder()
                .scheduleId(savedFlightSchedule.getId())
                .flightId(flight.getId())
                .arrivalAirportId(flight.getArrivalAirportId())
                .departureAirportId(flight.getDepartureAirportId())
                .flightInstanceStatus(FlightStatus.SCHEDULED)
                .build();
    }

    @Override
    public FlightScheduleResponse getFlightScheduleById(Long flightScheduleId) {
        return null;
    }

    @Override
    public List<FlightScheduleResponse> getFlightScheduleByAirline(Long userId) {
        return List.of();
    }

    @Override
    public FlightScheduleResponse updateFlightSchedule(Long flightScheduleId, FlightScheduleRequest flightScheduleRequest) {
        return null;
    }

    @Override
    public void deleteFlightScheduleById(Long flightScheduleId) {

    }
}
