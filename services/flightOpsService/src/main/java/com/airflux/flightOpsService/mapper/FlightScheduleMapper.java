package com.airflux.flightOpsService.mapper;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.entity.FlightSchedule;
import com.airflux.payload.request.FlightScheduleRequest;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightScheduleResponse;

public class FlightScheduleMapper {

    public static FlightSchedule toEntity(FlightScheduleRequest flightScheduleRequest, Flight flight) {

        if (flightScheduleRequest == null || flight == null) return null;

        return FlightSchedule.builder()
                .scheduledFlight(flight)
                .departureAirportId(flight.getDepartureAirportId())
                .arrivalAirportId(flight.getArrivalAirportId())
                .departureTime(flightScheduleRequest.getDepartureTime())
                .arrivalTime(flightScheduleRequest.getArrivalTime())
                .startDate(flightScheduleRequest.getStartDate())
                .endDate(flightScheduleRequest.getEndDate())
                .operatingDays(flightScheduleRequest.getOperatingDays())
                .isActive(flightScheduleRequest.getIsActive() != null ? flightScheduleRequest.getIsActive() : true)
                .build();
    }

    public static FlightScheduleResponse toResponse(FlightSchedule flightSchedule,
                                                    AirportResponse departureAirport,
                                                    AirportResponse arrivalAirport) {

        if (flightSchedule == null) return null;

        return FlightScheduleResponse.builder()
                .id(flightSchedule.getId())
                .flightId(flightSchedule.getScheduledFlight() != null ?
                        flightSchedule.getScheduledFlight().getId() : null)
                .flightNumber(flightSchedule.getScheduledFlight() != null ?
                        flightSchedule.getScheduledFlight().getFlightNumber() : null)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureTime(flightSchedule.getDepartureTime())
                .arrivalTime(flightSchedule.getArrivalTime())
                .startDate(flightSchedule.getStartDate())
                .endDate(flightSchedule.getEndDate())
                .operatingDays(flightSchedule.getOperatingDays())
                .isActive(flightSchedule.getIsActive())
                .build();
    }

    public static void updateFlightSchedule(FlightScheduleRequest flightScheduleRequest, FlightSchedule existingFlightSchedule) {

        if (flightScheduleRequest == null || existingFlightSchedule == null) return;

        if(flightScheduleRequest.getDepartureTime() != null) existingFlightSchedule
                .setDepartureTime(flightScheduleRequest.getDepartureTime());

        if(flightScheduleRequest.getArrivalTime() != null) existingFlightSchedule
                .setArrivalTime(flightScheduleRequest.getArrivalTime());

        if(flightScheduleRequest.getStartDate() != null) existingFlightSchedule
                .setStartDate(flightScheduleRequest.getStartDate());

        if(flightScheduleRequest.getEndDate() != null) existingFlightSchedule
                .setEndDate(flightScheduleRequest.getEndDate());

        if(flightScheduleRequest.getOperatingDays() != null) existingFlightSchedule
                .setOperatingDays(flightScheduleRequest.getOperatingDays());

        if(flightScheduleRequest.getIsActive() != null) existingFlightSchedule
                .setIsActive(flightScheduleRequest.getIsActive());

    }
}
