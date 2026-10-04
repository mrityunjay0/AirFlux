package com.airflux.pricingService.mapper;

import com.airflux.payload.embeddable.*;
import com.airflux.payload.request.FareRequest;
import com.airflux.pricingService.entity.Fare;

import java.math.BigDecimal;

public class FareMapper {

    public static Fare toEntity(FareRequest fareRequest) {

        if(fareRequest == null) return null;

        return Fare.builder()
                .name(fareRequest.getName())
                .rbdCode(fareRequest.getRbdCode())
                .flightId(fareRequest.getFlightId())
                .cabinClassId(fareRequest.getCabinClassId())
                .baseFare(fareRequest.getBaseFare())
                .taxesAndFees(fareRequest.getTaxesAndFees())
                .airlineFees(fareRequest.getAirlineFees())
                .currentPrice(calculatedPrice(fareRequest))
                .fareLabel(fareRequest.getFareLabel())
                .seatBenefits(toSeatBenefits(fareRequest))
                .boardingBenefits(toBoardingBenefits(fareRequest))
                .inFlightBenefits(toInFlightBenefits(fareRequest))
                .flexibilityBenefits(toFlexibilityBenefits(fareRequest))
                .premiumServiceBenefits(toPremiumServiceBenefits(fareRequest))
                .build();
    }

    // Calculate the current price based on base fare, taxes and fees, and airline fees
    private static BigDecimal calculatedPrice(FareRequest fareRequest) {

        BigDecimal baseFare = fareRequest.getBaseFare();
        BigDecimal taxesAndFees = fareRequest.getTaxesAndFees();
        BigDecimal airlineFees = fareRequest.getAirlineFees();

        if (baseFare == null) baseFare = BigDecimal.ZERO;
        if (taxesAndFees == null) taxesAndFees = BigDecimal.ZERO;
        if (airlineFees == null) airlineFees = BigDecimal.ZERO;

        return baseFare.add(taxesAndFees).add(airlineFees);
    }

    // Convert FareRequest to SeatBenefits
    private static SeatBenefits toSeatBenefits(FareRequest request) {

        return SeatBenefits.builder()
                .extraSeatSpace(defaultFalse(request.getExtraSeatSpace()))
                .preferredSeatChoice(defaultFalse(request.getPreferredSeatChoice()))
                .advanceSeatSelection(defaultFalse(request.getAdvanceSeatSelection()))
                .guaranteedSeatTogether(defaultFalse(request.getGuaranteedSeatTogether()))
                .build();
    }

    // Convert FareRequest to BoardingBenefits
    private static BoardingBenefits toBoardingBenefits(FareRequest request) {

        return BoardingBenefits.builder()
                .priorityBoarding(defaultFalse(request.getPriorityBoarding()))
                .priorityCheckIn(defaultFalse(request.getPriorityCheckIn()))
                .fastTrackSecurity(defaultFalse(request.getFastTrackSecurity()))
                .build();
    }

    // Convert FareRequest to InFlightBenefits
    private static InFlightBenefits toInFlightBenefits(FareRequest request) {

        return InFlightBenefits.builder()
                .complimentaryMeals(defaultFalse(request.getComplimentaryMeals()))
                .premiumMealChoice(defaultFalse(request.getPremiumMealChoice()))
                .inflightInternet(defaultFalse(request.getInflightInternet()))
                .inFlightEntertainment(defaultFalse(request.getInFlightEntertainment()))
                .complimentaryBeverages(defaultFalse(request.getComplimentaryBeverages()))
                .build();
    }

    // Convert FareRequest to FlexibilityBenefits
    private static FlexibilityBenefits toFlexibilityBenefits(FareRequest request) {

        return FlexibilityBenefits.builder()
                .freeDateChange(defaultFalse(request.getFreeDateChange()))
                .partialRefund(defaultFalse(request.getPartialRefund()))
                .fullRefund(defaultFalse(request.getFullRefund()))
                .build();
    }

    // Convert FareRequest to PremiumServiceBenefits
    private static PremiumServiceBenefits toPremiumServiceBenefits(FareRequest request) {

        return PremiumServiceBenefits.builder()
                .loungeAccess(defaultFalse(request.getLoungeAccess()))
                .airportTransfer(defaultFalse(request.getAirportTransfer()))
                .build();
    }

    // Convert null Boolean values to false
    private static Boolean defaultFalse(Boolean value) {
        return value != null && value;
    }

}
