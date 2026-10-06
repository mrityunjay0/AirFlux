package com.airflux.seatService.mapper;

import com.airflux.payload.request.SeatMapRequest;
import com.airflux.payload.response.SeatMapResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.SeatMap;

public class SeatMapMapper {

    public static SeatMap toEntity(SeatMapRequest request, CabinClass cabinClass, Long airlineId) {

        if (request == null) return null;

        return SeatMap.builder()
                .name(request.getName())
                .totalRows(request.getTotalRows())
                .leftSeatsPerRow(request.getLeftSeatsPerRow())
                .rightSeatsPerRow(request.getRightSeatsPerRow())
                .airlineId(airlineId)
                .cabinClass(cabinClass)
                .build();
    }

    public static SeatMapResponse toResponse(SeatMap seatMap) {

        if (seatMap == null) return null;

        int totalSeats =
                seatMap.getTotalRows()
                        * (seatMap.getLeftSeatsPerRow()
                        + seatMap.getRightSeatsPerRow());


        return SeatMapResponse.builder()
                .id(seatMap.getId())
                .name(seatMap.getName())
                .totalRows(seatMap.getTotalRows())
                .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
                .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
                .airlineId(seatMap.getAirlineId())
                .cabinClassId(
                        seatMap.getCabinClass() != null
                                ? seatMap.getCabinClass().getId() : null)
                .cabinClassCode(
                        seatMap.getCabinClass() != null
                                ? seatMap.getCabinClass().getCode() : null)
                .cabinClassName(seatMap.getCabinClass() != null
                                ? seatMap.getCabinClass().getType() : null)
                .totalSeats(totalSeats)
                .build();
    }

    public static void updateEntity(SeatMap seatMap, SeatMapRequest request) {

        if (seatMap == null || request == null) return;

        if (request.getName() != null) {
            seatMap.setName(request.getName());
        }

        if (request.getTotalRows() != null) {
            seatMap.setTotalRows(request.getTotalRows());
        }

        if (request.getLeftSeatsPerRow() != null) {
            seatMap.setLeftSeatsPerRow(request.getLeftSeatsPerRow());
        }

        if (request.getRightSeatsPerRow() != null) {
            seatMap.setRightSeatsPerRow(request.getRightSeatsPerRow());
        }
    }
}
