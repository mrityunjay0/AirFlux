package com.airflux.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggagePolicyResponse {

    private Long id;
    private Long fareId;
    private Long airlineId;

    private String name;
    private String description;

    private Double cabinBaggageMaxWeight;
    private Integer cabinBaggagePieces;
    private Double cabinBaggageWeightPerPeice;

    private Double checkInBaggageMaxWeight;
    private Integer checkInBaggagePieces;
    private Double checkInBaggageWeightPerPiece;

    private Integer freeCheckedBagsAllowance;

    private Boolean priorityBaggage;
    private Boolean extraBaggageAllowance;

    private Instant createdAt;
    private Instant updatedAt;
}
