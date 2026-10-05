package com.airflux.pricingService.mapper;

import com.airflux.payload.request.BaggagePolicyRequest;
import com.airflux.payload.response.BaggagePolicyResponse;
import com.airflux.pricingService.entity.BaggagePolicy;
import com.airflux.pricingService.entity.Fare;

public class BaggagePolicyMapper {

    public static BaggagePolicy toEntity(BaggagePolicyRequest request, Fare fare) {

        if (request == null) return null;

        return BaggagePolicy.builder()
                .fare(fare)
                .name(request.getName())
                .description(request.getDescription())
                .cabinBaggageMaxWeight(request.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(request.getCabinBaggagePieces())
                .cabinBaggageWeightPerPiece(request.getCabinBaggageWeightPerPeice())
                .checkInBaggageMaxWeight(request.getCheckInBaggageMaxWeight())
                .chckinBaggagePeices(request.getChckinBaggagePieces())
                .checkInBaggageWeightPerPiece(request.getCheckInBaggageWeightPerPeice())
                .freeCheckedBagsAllowance(request.getFreeCheckedBagsAllowance())
                .priorityBaggage(request.getPriorityBaggage())
                .extraBaggageAllowance(request.getExtraBaggageAllowance())
                .build();
    }

    public static BaggagePolicyResponse toResponse(BaggagePolicy baggagePolicy) {

        if (baggagePolicy == null) return null;

        return BaggagePolicyResponse.builder()
                .id(baggagePolicy.getId())
                .fareId(baggagePolicy.getFare() != null ? baggagePolicy.getFare().getId() : null)
                .airlineId(baggagePolicy.getAirlineId())
                .name(baggagePolicy.getName())
                .description(baggagePolicy.getDescription())
                .cabinBaggageMaxWeight(baggagePolicy.getCabinBaggageMaxWeight())
                .cabinBaggagePieces(baggagePolicy.getCabinBaggagePieces())
                .cabinBaggageWeightPerPeice(baggagePolicy.getCabinBaggageWeightPerPiece())
                .checkInBaggageMaxWeight(baggagePolicy.getCheckInBaggageMaxWeight())
                .checkInBaggagePieces(baggagePolicy.getChckinBaggagePeices())
                .checkInBaggageWeightPerPiece(baggagePolicy.getCheckInBaggageWeightPerPiece())
                .freeCheckedBagsAllowance(baggagePolicy.getFreeCheckedBagsAllowance())
                .priorityBaggage(baggagePolicy.getPriorityBaggage())
                .extraBaggageAllowance(baggagePolicy.getExtraBaggageAllowance())
                .createdAt(baggagePolicy.getCreatedAt())
                .updatedAt(baggagePolicy.getUpdatedAt())
                .build();
    }


    public static void updateEntity(BaggagePolicyRequest request, BaggagePolicy entity) {

        if (request == null || entity == null) return;

        if (request.getName() != null) {
            entity.setName(request.getName());
        }

        if (request.getDescription() != null) {
            entity.setDescription(request.getDescription());
        }

        if (request.getCabinBaggageMaxWeight() != null) {
            entity.setCabinBaggageMaxWeight(
                    request.getCabinBaggageMaxWeight()
            );
        }

        if (request.getCabinBaggagePieces() != null) {
            entity.setCabinBaggagePieces(
                    request.getCabinBaggagePieces()
            );
        }

        if(request.getCabinBaggageWeightPerPeice() != null) {
            entity.setCabinBaggageWeightPerPiece(
                    request.getCabinBaggageWeightPerPeice()
            );
        }

        if (request.getCheckInBaggageMaxWeight() != null) {
            entity.setCheckInBaggageMaxWeight(
                    request.getCheckInBaggageMaxWeight()
            );
        }

        if (request.getChckinBaggagePieces() != null) {
            entity.setChckinBaggagePeices(
                    request.getChckinBaggagePieces()
            );
        }

        if (request.getCheckInBaggageWeightPerPeice() != null) {
            entity.setCheckInBaggageWeightPerPiece(
                    request.getCheckInBaggageWeightPerPeice()
            );
        }

        if (request.getFreeCheckedBagsAllowance() != null) {
            entity.setFreeCheckedBagsAllowance(
                    request.getFreeCheckedBagsAllowance()
            );
        }

        if (request.getPriorityBaggage() != null) {
            entity.setPriorityBaggage(
                    request.getPriorityBaggage()
            );
        }

        if (request.getExtraBaggageAllowance() != null) {
            entity.setExtraBaggageAllowance(
                    request.getExtraBaggageAllowance()
            );
        }
    }

}