package com.airflux.flightOpsService.mapper;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.flightOpsService.entity.FlightInstance;
import com.airflux.payload.enums.FlightStatus;
import com.airflux.payload.request.FlightInstanceRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.AirlineResponse;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightInstanceResponse;


public class FlightInstanceMapper {

    public static FlightInstance toEntity(FlightInstanceRequest flightInstanceRequest, Flight flight) {

        if (flight == null) return null;

        return FlightInstance.builder()
                .flight(flight)
                .airlineId(flight.getAirlineId())
                .scheduleId(flightInstanceRequest.getScheduleId())
                .departureAirportId(
                        flightInstanceRequest.getDepartureAirportId() != null ?
                                flightInstanceRequest.getDepartureAirportId() : null
                )
                .arrivalAirportId(flightInstanceRequest.getArrivalAirportId() != null ?
                        flightInstanceRequest.getArrivalAirportId() : null
                )
                .departureDateTime(flightInstanceRequest.getDepartureDateTime())
                .arrivalDateTime(flightInstanceRequest.getArrivalDateTime())
                .flightInstanceStatus(FlightStatus.SCHEDULED)
                .minAdvanceBookingDays(flightInstanceRequest.getMinAdvanceBookingDays())
                .maxAdvanceBookingDays(flightInstanceRequest.getMaxAdvanceBookingDays())
                .isActive(flightInstanceRequest.getIsActive() != null ?
                        flightInstanceRequest.getIsActive() : true
                )
                .build();
    }

    public static FlightInstanceResponse toResponse(FlightInstance flightInstance,
                                                   AircraftResponse aircraftResponse,
                                                   AirlineResponse airlineResponse,
                                                   AirportResponse departureAirport,
                                                   AirportResponse arrivalAirport) {

        if (flightInstance == null) return null;

        return FlightInstanceResponse.builder()
                .id(flightInstance.getId())
                .flightId(flightInstance.getFlight() != null ? flightInstance.getFlight().getId() : null)
                .flightNumber(flightInstance.getFlight() != null ? flightInstance.getFlight().getFlightNumber() : null)
                .aircraftId(flightInstance.getFlight().getAircraftId())
                .aircraftModel(aircraftResponse.getModel())
                .aircraftCode(aircraftResponse.getCode())
                .airlineId(flightInstance.getAirlineId())
                .airlineName(airlineResponse.getName())
                .airlineLogo(airlineResponse.getLogoUrl())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(flightInstance.getDepartureDateTime())
                .arrivalDateTime(flightInstance.getArrivalDateTime())
                .formatedDuration(flightInstance.getFormatedDuration())
                .totalSeats(flightInstance.getTotalSeats())
                .availavleSeats(flightInstance.getAvailableSeats())
                .flightInstanceStatus(flightInstance.getFlightInstanceStatus())
                .minAdvanceBookingDays(flightInstance.getMinAdvanceBookingDays())
                .maxAdvanceBookingDays(flightInstance.getMaxAdvanceBookingDays())
                .isActive(flightInstance.getIsActive())
                .build();
    }

    public static void updateFlightInstance(FlightInstanceRequest flightInstanceRequest, FlightInstance existingFlightInstance) {

        if (flightInstanceRequest == null || existingFlightInstance == null) return;

        if(flightInstanceRequest.getDepartureAirportId() != null) existingFlightInstance
                .setDepartureAirportId(flightInstanceRequest.getDepartureAirportId());

        if(flightInstanceRequest.getArrivalAirportId() != null) existingFlightInstance
                .setArrivalAirportId(flightInstanceRequest.getArrivalAirportId());

        if(flightInstanceRequest.getDepartureDateTime() != null) existingFlightInstance
                .setDepartureDateTime(flightInstanceRequest.getDepartureDateTime());

        if(flightInstanceRequest.getArrivalDateTime() != null) existingFlightInstance
                .setArrivalDateTime(flightInstanceRequest.getArrivalDateTime());

        if(flightInstanceRequest.getAvailableSeats() != null) existingFlightInstance
                .setAvailableSeats(flightInstanceRequest.getAvailableSeats());

        if(flightInstanceRequest.getFlightInstanceStatus() != null) existingFlightInstance
                .setFlightInstanceStatus(flightInstanceRequest.getFlightInstanceStatus());

        if(flightInstanceRequest.getMinAdvanceBookingDays() != null) existingFlightInstance
                .setMinAdvanceBookingDays(flightInstanceRequest.getMinAdvanceBookingDays());

        if(flightInstanceRequest.getMaxAdvanceBookingDays() != null) existingFlightInstance
                .setMaxAdvanceBookingDays(flightInstanceRequest.getMaxAdvanceBookingDays());

        if(flightInstanceRequest.getIsActive() != null) existingFlightInstance
                .setIsActive(flightInstanceRequest.getIsActive());
        
    }
}
