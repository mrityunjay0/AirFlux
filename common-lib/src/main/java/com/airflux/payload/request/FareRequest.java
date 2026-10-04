package com.airflux.payload.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FareRequest {

    @NotBlank(message = "Fare name is required")
    private String name;

    @NotNull(message = "RBD code is required")
    private Character rbdCode;

    @NotNull(message = "Flight ID is required")
    private Long flightId;

    @NotNull(message = "Cabin class ID is required")
    private Long cabinClassId;

    @NotNull(message = "Base fare is required")
    @DecimalMin(value = "0.0", message = "Base fare cannot be negative")
    private BigDecimal baseFare;

    @DecimalMin(value = "0.0", message = "Taxes and fees cannot be negative")
    private BigDecimal taxesAndFees;

    @DecimalMin(value = "0.0", message = "Airline fees cannot be negative")
    private BigDecimal airlineFees;

    @DecimalMin(value = "0.0", message = "Current price cannot be negative")
    private BigDecimal currentPrice;

    @Size(max = 100, message = "Fare label cannot exceed 100 characters")
    private String fareLabel;

    //Seat Benefits
    private Boolean extraSeatSpace;
    private Boolean preferredSeatChoice;
    private Boolean advanceSeatSelection;
    private Boolean guaranteedSeatTogether;

    // Boarding Benefits
    private Boolean priorityBoarding;
    private Boolean priorityCheckIn;
    private Boolean fastTrackSecurity;

    // In-Flight Benefits
    private Boolean complimentaryMeals;
    private Boolean premiumMealChoice;
    private Boolean inflightInternet;
    private Boolean inFlightEntertainment;
    private Boolean complimentaryBeverages;

    // Flexibility Benefits
    private Boolean freeDateChange;
    private Boolean partialRefund;
    private Boolean fullRefund;

    // Premium Service Benefits
    private Boolean loungeAccess;
    private Boolean airportTransfer;

}