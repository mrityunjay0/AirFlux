package com.airflux.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FareRulesRequest {

    @NotBlank(message = "Rule name is required")
    private String ruleName;

    @NotNull(message = "Airline ID is required")
    @Positive(message = "Airline ID must be positive")
    private Long airlineId;

    @NotNull(message = "Fare ID is required")
    @Positive(message = "Fare ID must be positive")
    private Long fareId;

    @NotNull(message = "Refundable status is required")
    private Boolean isRefundable;

    @NotNull(message = "Change fee is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Change fee cannot be negative")
    private BigDecimal changeFee;

    @NotNull(message = "Cancellation fee is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Cancellation fee cannot be negative")
    private BigDecimal cancellationFee;

    @NotNull(message = "Refund deadline days is required")
    @Min(value = 0, message = "Refund deadline days cannot be negative")
    private Integer refundDeadlineDays;

    @NotNull(message = "Change deadline hours is required")
    @Min(value = 0, message = "Change deadline hours cannot be negative")
    private Integer changeDeadlineHours;

    @NotNull(message = "Changeable status is required")
    private Boolean isChangeable;
}