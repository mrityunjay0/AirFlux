package com.airflux.flightOpsService.mapper;

import com.airflux.flightOpsService.entity.Flight;
import com.airflux.payload.request.FlightRequest;
import com.airflux.payload.response.AircraftResponse;
import com.airflux.payload.response.AirlineResponse;
import com.airflux.payload.response.AirportResponse;
import com.airflux.payload.response.FlightResponse;

public class FlightMapper {

    public static Flight toEntity(FlightRequest flightRequest) {

        if (flightRequest == null) return null;

        return Flight.builder()
                .flightNumber(flightRequest.getFlightNumber())
                .aircraftId(flightRequest.getAircraftId())
                .departureAirportId(flightRequest.getDepartureAirportId())
                .arrivalAirportId(flightRequest.getArrivalAirportId())
                .flightStatus(flightRequest.getFlightStatus())
                .build();
    }

    public static FlightResponse toResponse(Flight flight, AircraftResponse aircraftResponse,
                                            AirlineResponse airlineResponse, AirportResponse departureAirportResponse,
                                            AirportResponse arrivalAirportResponse) {

        if  (flight == null) return null;

        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airlineResponse)
                .aircraft(aircraftResponse)
                .departureAirport(departureAirportResponse)
                .arrivalAirport(arrivalAirportResponse)
                .flightStatus(flight.getFlightStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }

    public static void updateFlight(FlightRequest flightRequest, Flight flight) {

        if (flight == null || flightRequest == null) return;

        if(flightRequest.getFlightNumber() != null) flight.setFlightNumber(flightRequest.getFlightNumber());
        if(flightRequest.getAirlineId() != null) flight.setAirlineId(flightRequest.getAirlineId());
        if(flightRequest.getAircraftId() != null) flight.setAircraftId(flightRequest.getAircraftId());
        if(flightRequest.getDepartureAirportId() != null) flight.setDepartureAirportId(flightRequest.getDepartureAirportId());
        if(flightRequest.getArrivalAirportId() != null) flight.setArrivalAirportId(flightRequest.getArrivalAirportId());
        if(flightRequest.getFlightStatus() != null) flight.setFlightStatus(flightRequest.getFlightStatus());
    }
}
