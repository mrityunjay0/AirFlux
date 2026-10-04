package com.airflux.payload.response;

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
public class FareRulesResponse {

    private Long id;

    private String ruleName;

    private Long airlineId;

    private Long fareId;

    private Boolean isRefundable;

    private BigDecimal changeFee;

    private BigDecimal cancellationFee;

    private Integer refundDeadlineDays;

    private Integer changeDeadlineHours;

    private Boolean isChangeable;

    private Instant createdAt;

    private Instant updatedAt;
}