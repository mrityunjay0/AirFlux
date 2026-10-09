package com.airflux.seatService.mapper;

import com.airflux.payload.response.SeatResponse;
import com.airflux.seatService.entity.Seat;

public class SeatMapper {

    public static SeatResponse toResponse(Seat seat) {

        if(seat == null) return null;

        return SeatResponse.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .seatRow(seat.getSeatRow())
                .columnLetter(seat.getColumnLetter())
                .seatType(seat.getSeatType())
                .isAvailable(seat.getIsAvailable())
                .isBlocked(seat.getIsBlocked())
                .isActive(seat.getIsActive())
                .isEmergencyExit(seat.getIsEmergencyExit())
                .basePrice(seat.getBasePrice())
                .premiumSurCharge(seat.getPremiumSurCharge())
                .totalPrice(seat.getTotalPrice())
                .hasExtraLegRoom(seat.getHasExtraLegRoom())
                .hasPowerOutlet(seat.getHasPowerOutlet())
                .hasExtraWidth(seat.getHasExtraWidth())
                .hasTvScreen(seat.getHasTvScreen())
                .seatPitch(seat.getSeatPitch())
                .seatWidth(seat.getSeatWidth())
                .seatMapId(seat.getSeatMap() != null ? seat.getSeatMap().getId() : null)
                .seatMapName(seat.getSeatMap() != null ? seat.getSeatMap().getName() : null)
                .cabinClassId(seat.getCabinClass() != null ? seat.getCabinClass().getId() : null)
                .cabinClassName(seat.getCabinClass() != null ? seat.getCabinClass().getType().toString() : null)
                .createdAt(seat.getCreatedAt())
                .updatedAt(seat.getUpdatedAt())
                .createdBy(seat.getCreatedBy())
                .updatedBy(seat.getUpdatedBy())
//                .isPremiumSeat(seat.isPremiumSeat())
                .isBookable(seat.isBookable())
                .fullPosition(seat.getFullPosition())
//                .seatCharacteristics(seat.getSeatC)
                .build();
    }

    
}
