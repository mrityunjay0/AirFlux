package com.airflux.payload.request;

import com.airflux.payload.enums.AircraftStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftRequest {

    @NotBlank(message = "Code is required.")
    private String code;

    @NotBlank(message = "Model is required.")
    private String model;

    @NotBlank(message = "Manufacturer is required.")
    private String manufacturer;

    @NotNull(message = "Seating capacity is required.")
    @Positive(message = "seatingCapacity must be positive.")
    private Integer seatingCapacity;

    @Positive(message = "Economy seats must be positive.")
    private Integer economySeats;

    @Positive(message = "Premium economy seats must be positive.")
    private Integer premiumEconomySeats;

    @Positive(message = "Business seats must be positive.")
    private Integer businessSeats;

    @Positive(message = "First class seats must be positive.")
    private Integer firstClassSeats;

    @Positive(message = "Range must be positive.")
    private Integer rangeKm;

    @Positive(message = "Cruising speed must be positive.")
    private Integer cruisingSpeedKmh;

    @Positive(message = "Max altitude must be positive.")
    private Integer maxAltitude;

    @Positive(message = "year of manufacture must be positive.")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;

    private LocalDate nextMaintenanceDate;

    @NotNull(message = "Aircraft status is required.")
    private AircraftStatus status;

    @NotNull(message = "Availability status is required.")
    private Boolean isAvailable;

    private Long currentAirportId;

}
