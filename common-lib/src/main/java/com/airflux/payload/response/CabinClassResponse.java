package com.airflux.payload.response;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinClassResponse {

    private Long id;

    private String name;
    private String code;
    private String description;

    private Long aircraftId;

    private Integer displayOrder;

    private Boolean isActive;
    private Boolean isBookable;

    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;

    private String seatType;

    private Instant createdAt;
    private Instant updatedAt;

    private SeatMapResponse seatMapResponse;
}
