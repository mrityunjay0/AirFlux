package com.airflux.payload.response;

import com.airflux.payload.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponse {

    private Long id;

    private String seatNumber;
    private Integer seatRow;
    private Character columnLetter;

    private SeatType seatType;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExit;
    private Boolean isActive;

    private Double basePrice;
    private Double premiumSurCharge;
    private Double totalPrice;

    private Boolean hasExtraLegRoom;
    private Boolean hasPowerOutlet;
    private Boolean hasTvScreen;
    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;

    private Long seatMapId;
    private String seatMapName;
    private Long cabinClassId;
    private String cabinClassName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

//    private Boolean isPremiumSeat;
    private Boolean isBookable;
    private String fullPosition;
//    private String seatCharacteristics;

}
