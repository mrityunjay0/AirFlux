package com.airflux.payload.response;

import com.airflux.payload.enums.FlightStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlightResponse {

    private Long id;
    private String flightNumber;

    private AirlineResponse airline;
    private AircraftResponse aircraft;

    private AirportResponse departureAirport;
    private AirportResponse arrivalAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private FlightStatus flightStatus;

    private Double lowestPrice;
    private Integer totalAvailableSeats;

    private Instant createdAt;
    private Instant updatedAt;

}
