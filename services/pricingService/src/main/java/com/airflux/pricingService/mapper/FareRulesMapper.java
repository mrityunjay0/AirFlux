package com.airflux.pricingService.mapper;

import com.airflux.payload.request.FareRulesRequest;
import com.airflux.payload.response.FareRulesResponse;
import com.airflux.pricingService.entity.Fare;
import com.airflux.pricingService.entity.FareRules;

public class FareRulesMapper {

    public static FareRules toEntity(FareRulesRequest request, Fare fare) {

        if (request == null) return null;

        return FareRules.builder()
                .ruleName(request.getRuleName())
                .airlineId(request.getAirlineId())
                .fare(fare)
                .isRefundable(request.getIsRefundable())
                .changeFee(request.getChangeFee())
                .cancellationFee(request.getCancellationFee())
                .refundDeadlineDays(request.getRefundDeadlineDays())
                .changeDeadlineHours(request.getChangeDeadlineHours())
                .isChangeable(request.getIsChangeable())
                .build();
    }

    public static FareRulesResponse toResponse(FareRules fareRules) {

        if (fareRules == null) return null;

        return FareRulesResponse.builder()
                .id(fareRules.getId())
                .ruleName(fareRules.getRuleName())
                .airlineId(fareRules.getAirlineId())
                .fareId(fareRules.getFare() != null ? fareRules.getFare().getId() : null)
                .isRefundable(fareRules.getIsRefundable())
                .changeFee(fareRules.getChangeFee())
                .cancellationFee(fareRules.getCancellationFee())
                .refundDeadlineDays(fareRules.getRefundDeadlineDays())
                .changeDeadlineHours(fareRules.getChangeDeadlineHours())
                .isChangeable(fareRules.getIsChangeable())
                .createdAt(fareRules.getCreatedAt())
                .updatedAt(fareRules.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FareRules fareRules, FareRulesRequest request, Fare fare) {

        if (fareRules == null || request == null) {
            return;
        }

        if (request.getRuleName() != null) {
            fareRules.setRuleName(request.getRuleName());
        }

        if (request.getAirlineId() != null) {
            fareRules.setAirlineId(request.getAirlineId());
        }

        if (fare != null) {
            fareRules.setFare(fare);
        }

        if (request.getIsRefundable() != null) {
            fareRules.setIsRefundable(request.getIsRefundable());
        }

        if (request.getChangeFee() != null) {
            fareRules.setChangeFee(request.getChangeFee());
        }

        if (request.getCancellationFee() != null) {
            fareRules.setCancellationFee(request.getCancellationFee());
        }

        if (request.getRefundDeadlineDays() != null) {
            fareRules.setRefundDeadlineDays(request.getRefundDeadlineDays());
        }

        if (request.getChangeDeadlineHours() != null) {
            fareRules.setChangeDeadlineHours(request.getChangeDeadlineHours());
        }

        if (request.getIsChangeable() != null) {
            fareRules.setIsChangeable(request.getIsChangeable());
        }
    }

}