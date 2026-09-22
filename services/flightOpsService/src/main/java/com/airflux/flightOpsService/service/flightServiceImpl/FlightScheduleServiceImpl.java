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
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightScheduleResponse;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {

            if (operatingDays.contains(date.getDayOfWeek())) {

                flightInstanceRequest.setDepartureDateTime(
                        LocalDateTime.of(date, savedFlightSchedule.getDepartureTime())
                );

                flightInstanceRequest.setArrivalDateTime(
                        LocalDateTime.of(date, savedFlightSchedule.getArrivalTime())
                );

                flightInstanceService.createFlightInstance(flightInstanceRequest, airlineId);
            }
        }

        return convertToFlightScheduleResponse(savedFlightSchedule);
    }


    @Override
    public FlightScheduleResponse getFlightScheduleById(Long flightScheduleId) {

        FlightSchedule flightSchedule = flightScheduleRepository.findById(flightScheduleId).orElseThrow(
                () -> new ResourceNotFoundException("Flight schedule with id: " +
                        flightScheduleId + " not found.")
        );

        return convertToFlightScheduleResponse(flightSchedule);
    }


    @Override
    public List<FlightScheduleResponse> getFlightScheduleByAirline(Long airlineId) {

        // todo: watch airlineId
        List<FlightSchedule> flightScheduleList = flightScheduleRepository.findByFlightAirlineId(airlineId);

        return flightScheduleList.stream().map(
                this::convertToFlightScheduleResponse
        ).toList();
    }


    @Override
    public FlightScheduleResponse updateFlightSchedule(Long flightScheduleId, FlightScheduleRequest flightScheduleRequest) {

        FlightSchedule flightSchedule = flightScheduleRepository.findById(flightScheduleId).orElseThrow(
                () -> new ResourceNotFoundException("Flight schedule with id: " +
                        flightScheduleId + " not found.")
        );

        FlightScheduleMapper.updateFlightSchedule(flightScheduleRequest, flightSchedule);
        FlightSchedule savedFlightSchedule = flightScheduleRepository.save(flightSchedule);

        return convertToFlightScheduleResponse(savedFlightSchedule);
    }


    @Override
    public void deleteFlightScheduleById(Long flightScheduleId) {

        FlightSchedule flightSchedule = flightScheduleRepository.findById(flightScheduleId).orElseThrow(
                () -> new ResourceNotFoundException("Flight schedule with id: " +
                        flightScheduleId + " not found.")
        );

        flightScheduleRepository.delete(flightSchedule);
    }


    public FlightScheduleResponse convertToFlightScheduleResponse(FlightSchedule savedFlightSchedule) {

        // todo : interservice communication

        AirportResponse departureAirport = AirportResponse.builder()
                .id(savedFlightSchedule.getDepartureAirportId())
                .build();

        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(savedFlightSchedule.getArrivalAirportId())
                .build();

        return FlightScheduleMapper.toResponse(
                savedFlightSchedule, departureAirport, arrivalAirport
        );
    }
}
