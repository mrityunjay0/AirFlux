package com.airflux.seatService.mapper;

import com.airflux.payload.request.SeatRequest;
import com.airflux.payload.response.SeatResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.Seat;
import com.airflux.seatService.entity.SeatMap;

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
                .hasTvScreen(seat.getHasTvScreen())
                .hasExtraWidth(seat.getHasExtraWidth())

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

                .isBookable(seat.isBookable())
                .fullPosition(seat.getFullPosition())

                .build();
    }

    public static void updateEntity(SeatRequest request, Seat seat, SeatMap seatMap, CabinClass cabinClass) {

        seat.setSeatNumber(request.getSeatNumber());
        seat.setSeatRow(request.getSeatRow());
        seat.setColumnLetter(request.getColumnLetter());
        seat.setSeatType(request.getSeatType());
        seat.setSeatMap(seatMap);
        seat.setCabinClass(cabinClass);

        if (request.getIsAvailable() != null) seat.setIsAvailable(request.getIsAvailable());
        if (request.getIsBlocked() != null) seat.setIsBlocked(request.getIsBlocked());
        if (request.getIsEmergencyExit() != null) seat.setIsEmergencyExit(request.getIsEmergencyExit());
        if (request.getIsActive() != null) seat.setIsActive(request.getIsActive());

        seat.setBasePrice(request.getBasePrice());
        seat.setPremiumSurCharge(request.getPremiumSurCharge());

        if (request.getHasExtraLegRoom() != null) seat.setHasExtraLegRoom(request.getHasExtraLegRoom());
        if (request.getHasBassinet() != null) seat.setHasBassinet(request.getHasBassinet());
        if (request.getIsNearLavatory() != null) seat.setIsNearLavatory(request.getIsNearLavatory());
        if (request.getIsNearGallery() != null) seat.setIsNearGallery(request.getIsNearGallery());
        if (request.getHasPowerOutlet() != null) seat.setHasPowerOutlet(request.getHasPowerOutlet());
        if (request.getHasTvScreen() != null) seat.setHasTvScreen(request.getHasTvScreen());
        if (request.getHasExtraWidth() != null) seat.setHasExtraWidth(request.getHasExtraWidth());

        seat.setSeatPitch(request.getSeatPitch());
        seat.setSeatWidth(request.getSeatWidth());
    }

}
