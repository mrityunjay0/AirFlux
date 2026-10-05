package com.airflux.payload.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaggagePolicyRequest {

    @NotBlank(message = "Baggage policy name is required")
    private String name;

    @NotNull(message = "fareId is required")
    private Long fareId;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @DecimalMin(value = "0.0", inclusive = false, message = "Cabin baggage maximum weight must be greater than 0")
    private Double cabinBaggageMaxWeight;

    @Min(value = 0, message = "Cabin baggage pieces cannot be negative")
    private Integer cabinBaggagePieces;

    @DecimalMin(value = "0.0", inclusive = false, message = "Check-in baggage weight per piece must be greater than 0")
    private Double cabinBaggageWeightPerPeice;

    @DecimalMin(value = "0.0", inclusive = false, message = "Check-in baggage maximum weight must be greater than 0")
    private Double checkInBaggageMaxWeight;

    @Min(value = 0, message = "Check-in baggage pieces cannot be negative")
    private Integer chckinBaggagePieces;

    @DecimalMin(value = "0.0", inclusive = false, message = "Check-in baggage weight per piece must be greater than 0")
    private Double checkInBaggageWeightPerPeice;

    @Min(value = 0, message = "Free checked bags allowance cannot be negative")
    private Integer freeCheckedBagsAllowance;

    private Boolean priorityBaggage;

    private Boolean extraBaggageAllowance;
}
