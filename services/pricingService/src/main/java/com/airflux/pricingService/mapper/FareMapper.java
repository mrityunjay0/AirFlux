package com.airflux.pricingService.mapper;

import com.airflux.payload.embeddable.*;
import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.FareResponse;
import com.airflux.pricingService.entity.Fare;

import java.math.BigDecimal;

public class FareMapper {

    // Convert FareRequest DTO to Fare entity
    public static Fare toEntity(FareRequest fareRequest) {

        if(fareRequest == null) return null;

        return Fare.builder()
                // Basic Information
                .name(fareRequest.getName())
                .rbdCode(fareRequest.getRbdCode())
                .flightId(fareRequest.getFlightId())
                .cabinClassId(fareRequest.getCabinClassId())

                // Pricing
                .baseFare(fareRequest.getBaseFare())
                .taxesAndFees(fareRequest.getTaxesAndFees())
                .airlineFees(fareRequest.getAirlineFees())
                .currentPrice(calculatedPrice(fareRequest))

                // Fare Information
                .fareLabel(fareRequest.getFareLabel())

                // Benefits
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

    // Convert Fare entity to FareResponse DTO
    public static FareResponse toResponse(Fare fare) {

        if (fare == null) return null;

        return FareResponse.builder()

                .id(fare.getId())
                .name(fare.getName())
                .rbdCode(fare.getRbdCode())
                .flightId(fare.getFlightId())
                .cabinClassId(fare.getCabinClassId())
                .cabinClassType(fare.getCabinClassType())

                .baseFare(fare.getBaseFare())
                .taxesAndFees(fare.getTaxesAndFees())
                .airlineFees(fare.getAirlineFees())
                .currentPrice(fare.getCurrentPrice())
                .totalPrice(calculateTotalPrice(fare))
                .fareLabel(fare.getFareLabel())
                .fareRulesId(fare.getFareRules() != null ? fare.getFareRules().getId() : null)

                .extraSeatSpace(
                        fare.getSeatBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getSeatBenefits().getExtraSeatSpace()
                        )
                )
                .preferredSeatChoice(
                        fare.getSeatBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getSeatBenefits().getPreferredSeatChoice()
                        )
                )
                .advanceSeatSelection(
                        fare.getSeatBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getSeatBenefits().getAdvanceSeatSelection()
                        )
                )
                .guaranteedSeatTogether(
                        fare.getSeatBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getSeatBenefits().getGuaranteedSeatTogether()
                        )
                )

                .priorityBoarding(
                        fare.getBoardingBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getBoardingBenefits().getPriorityBoarding()
                        )
                )
                .priorityCheckIn(
                        fare.getBoardingBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getBoardingBenefits().getPriorityCheckIn()
                        )
                )
                .fastTrackSecurity(
                        fare.getBoardingBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getBoardingBenefits().getFastTrackSecurity()
                        )
                )

                .complimentaryMeals(
                        fare.getInFlightBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getInFlightBenefits().getComplimentaryMeals()
                        )
                )
                .premiumMealChoice(
                        fare.getInFlightBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getInFlightBenefits().getPremiumMealChoice()
                        )
                )
                .inflightInternet(
                        fare.getInFlightBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getInFlightBenefits().getInflightInternet()
                        )
                )
                .inFlightEntertainment(
                        fare.getInFlightBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getInFlightBenefits().getInFlightEntertainment()
                        )
                )
                .complimentaryBeverages(
                        fare.getInFlightBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getInFlightBenefits().getComplimentaryBeverages()
                        )
                )

                .freeDateChange(
                        fare.getFlexibilityBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getFlexibilityBenefits().getFreeDateChange()
                        )
                )
                .partialRefund(
                        fare.getFlexibilityBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getFlexibilityBenefits().getPartialRefund()
                        )
                )
                .fullRefund(
                        fare.getFlexibilityBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getFlexibilityBenefits().getFullRefund()
                        )
                )

                .loungeAccess(
                        fare.getPremiumServiceBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getPremiumServiceBenefits().getLoungeAccess()
                        )
                )
                .airportTransfer(
                        fare.getPremiumServiceBenefits() != null
                                && Boolean.TRUE.equals(
                                fare.getPremiumServiceBenefits().getAirportTransfer()
                        )
                )
                .fareRuleResponse(fare.getFareRules() != null ?
                        FareRulesMapper.toResponse(fare.getFareRules()) : null)
                .baggagePolicyResponse(fare.getBaggagePolicy() != null ?
                        BaggagePolicyMapper.toResponse(fare.getBaggagePolicy()) : null)

                .createdAt(fare.getCreatedAt())
                .updatedAt(fare.getUpdatedAt())

                .build();
    }

    // Calculate the total price by summing base fare, taxes and fees, and current price
    private static BigDecimal calculateTotalPrice(Fare fare) {

        BigDecimal baseFare = fare.getBaseFare();
        BigDecimal taxesAndFees = fare.getTaxesAndFees();
        BigDecimal currentPrice = fare.getCurrentPrice();

        if (baseFare == null) baseFare = BigDecimal.ZERO;
        if (taxesAndFees == null) taxesAndFees = BigDecimal.ZERO;
        if (currentPrice == null) currentPrice = BigDecimal.ZERO;

        return baseFare
                .add(taxesAndFees)
                .add(currentPrice);
    }


    // To update
    public static void updateEntity(Fare fare, FareRequest request) {

        if (fare == null || request == null) {
            return;
        }

        // Basic Information
        if (request.getName() != null) {
            fare.setName(request.getName());
        }

        if (request.getRbdCode() != null) {
            fare.setRbdCode(request.getRbdCode());
        }

        if (request.getFlightId() != null) {
            fare.setFlightId(request.getFlightId());
        }

        if (request.getCabinClassId() != null) {
            fare.setCabinClassId(request.getCabinClassId());
        }

        // Pricing
        if (request.getBaseFare() != null) {
            fare.setBaseFare(request.getBaseFare());
        }

        if (request.getTaxesAndFees() != null) {
            fare.setTaxesAndFees(request.getTaxesAndFees());
        }

        if (request.getAirlineFees() != null) {
            fare.setAirlineFees(request.getAirlineFees());
        }

        // Recalculate current price only when pricing fields are supplied
        if (request.getBaseFare() != null
                || request.getTaxesAndFees() != null
                || request.getAirlineFees() != null) {

            fare.setCurrentPrice(
                    calculatedPrice(
                            fare.getBaseFare(),
                            fare.getTaxesAndFees(),
                            fare.getAirlineFees()
                    )
            );
        }

        // Fare Information
        if (request.getFareLabel() != null) {
            fare.setFareLabel(request.getFareLabel());
        }

        // Seat Benefits
        if (request.getExtraSeatSpace() != null) {
            fare.getSeatBenefits().setExtraSeatSpace(
                    request.getExtraSeatSpace()
            );
        }

        if (request.getPreferredSeatChoice() != null) {
            fare.getSeatBenefits().setPreferredSeatChoice(
                    request.getPreferredSeatChoice()
            );
        }

        if (request.getAdvanceSeatSelection() != null) {
            fare.getSeatBenefits().setAdvanceSeatSelection(
                    request.getAdvanceSeatSelection()
            );
        }

        if (request.getGuaranteedSeatTogether() != null) {
            fare.getSeatBenefits().setGuaranteedSeatTogether(
                    request.getGuaranteedSeatTogether()
            );
        }

        // Boarding Benefits
        if (request.getPriorityBoarding() != null) {
            fare.getBoardingBenefits().setPriorityBoarding(
                    request.getPriorityBoarding()
            );
        }

        if (request.getPriorityCheckIn() != null) {
            fare.getBoardingBenefits().setPriorityCheckIn(
                    request.getPriorityCheckIn()
            );
        }

        if (request.getFastTrackSecurity() != null) {
            fare.getBoardingBenefits().setFastTrackSecurity(
                    request.getFastTrackSecurity()
            );
        }

        // In-Flight Benefits
        if (request.getComplimentaryMeals() != null) {
            fare.getInFlightBenefits().setComplimentaryMeals(
                    request.getComplimentaryMeals()
            );
        }

        if (request.getPremiumMealChoice() != null) {
            fare.getInFlightBenefits().setPremiumMealChoice(
                    request.getPremiumMealChoice()
            );
        }

        if (request.getInflightInternet() != null) {
            fare.getInFlightBenefits().setInflightInternet(
                    request.getInflightInternet()
            );
        }

        if (request.getInFlightEntertainment() != null) {
            fare.getInFlightBenefits().setInFlightEntertainment(
                    request.getInFlightEntertainment()
            );
        }

        if (request.getComplimentaryBeverages() != null) {
            fare.getInFlightBenefits().setComplimentaryBeverages(
                    request.getComplimentaryBeverages()
            );
        }

        // Flexibility Benefits
        if (request.getFreeDateChange() != null) {
            fare.getFlexibilityBenefits().setFreeDateChange(
                    request.getFreeDateChange()
            );
        }

        if (request.getPartialRefund() != null) {
            fare.getFlexibilityBenefits().setPartialRefund(
                    request.getPartialRefund()
            );
        }

        if (request.getFullRefund() != null) {
            fare.getFlexibilityBenefits().setFullRefund(
                    request.getFullRefund()
            );
        }

        // Premium Service Benefits
        if (request.getLoungeAccess() != null) {
            fare.getPremiumServiceBenefits().setLoungeAccess(
                    request.getLoungeAccess()
            );
        }

        if (request.getAirportTransfer() != null) {
            fare.getPremiumServiceBenefits().setAirportTransfer(
                    request.getAirportTransfer()
            );
        }
    }

    private static BigDecimal calculatedPrice(BigDecimal baseFare, BigDecimal taxesAndFees, BigDecimal airlineFees) {

        if (baseFare == null) {
            baseFare = BigDecimal.ZERO;
        }

        if (taxesAndFees == null) {
            taxesAndFees = BigDecimal.ZERO;
        }

        if (airlineFees == null) {
            airlineFees = BigDecimal.ZERO;
        }

        return baseFare
                .add(taxesAndFees)
                .add(airlineFees);
    }
}
