package com.airflux.payload.request;

import com.airflux.payload.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {

    @NotBlank(message = "seat number is required")
    private String seatNumber;

    @NotNull(message = "seat row is required")
    private Integer seatRow;

    private Character columnLetter;

    @NotNull(message = "seat type is required")
    private SeatType seatType;

    @NotNull(message = "seat map id is required")
    private Long seatMapId;

    private Long cabinClassId;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExit;
    private Boolean isActive;

    private Double basePrice;
    private Double premiumSurCharge;

    private Boolean hasExtraLegRoom;
    private Boolean hasPowerOutlet;
    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;

}
