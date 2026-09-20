package com.airflux.payload.request;

import com.airflux.payload.enums.FlightStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlightRequest {

    @NotBlank(message = "Flight number is required.")
    @Size(max = 10)
    private String flightNumber;

    private String airlineId;

    @NotNull(message = "Aircraft id is required.")
    private Long aircraftId;

    @NotNull(message = "Departure airport id is required.")
    private Long departureAirportId;

    @NotNull(message = "Arrival airport id is required.")
    private Long arrivalAirportId;

    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

}
