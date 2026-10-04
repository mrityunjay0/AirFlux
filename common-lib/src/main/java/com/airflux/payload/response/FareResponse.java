package com.airflux.payload.response;

import com.airflux.payload.enums.CabinClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FareResponse {

    private Long id;
    private String name;
    private Character rbdCode;
    private Long flightId;
    private Long cabinClassId;
    private CabinClassType cabinClassType;

    // Pricing
    private BigDecimal baseFare;
    private BigDecimal taxesAndFees;
    private BigDecimal airlineFees;
    private BigDecimal currentPrice;
    private BigDecimal totalPrice;
    private String fareLabel;

    // Seat Benefits
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

    // Relationships
    private Long fareRulesId;
    private FareRuleResponse fareRuleResponse;
    private BaggagePolicyResponse baggagePolicyResponse;

    // Auditing
    private Instant createdAt;
    private Instant updatedAt;
}
