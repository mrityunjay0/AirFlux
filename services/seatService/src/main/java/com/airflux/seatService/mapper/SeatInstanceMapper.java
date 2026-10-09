package com.airflux.seatService.mapper;

import com.airflux.payload.enums.SeatAvailabilityStatus;
import com.airflux.payload.request.SeatInstanceRequest;
import com.airflux.payload.response.SeatInstanceResponse;
import com.airflux.seatService.entity.FlightInstanceCabin;
import com.airflux.seatService.entity.Seat;
import com.airflux.seatService.entity.SeatInstance;

public class SeatInstanceMapper {

    public static SeatInstance toEntity(SeatInstanceRequest request, Seat seat,
                                        FlightInstanceCabin flightInstanceCabin) {
        return SeatInstance.builder()
                .flightId(request.getFlightId())
                .seat(seat)
                .flightInstanceCabin(flightInstanceCabin)
                .flightInstanceId(request.getFlightInstanceId())
                .status(request.getStatus() != null ?
                        SeatAvailabilityStatus.valueOf(request.getStatus().toUpperCase()) :
                        SeatAvailabilityStatus.AVAILABLE)
                .fare(request.getFare())
                .build();
    }

    public static SeatInstanceResponse toResponse(SeatInstance si) {
        return SeatInstanceResponse.builder()
                .id(si.getId())
                .flightId(si.getFlightId())
                .seatId(si.getSeat() != null ? si.getSeat().getId() : null)
                .seatNumber(si.getSeat() != null ? si.getSeat().getSeatNumber() : null)
                .seatType(si.getSeat() != null ? si.getSeat().getSeatType().name() : null)
                .seatPosition(si.getSeat() != null ? si.getSeat().getFullPosition() : null)
                .seatResponse(SeatMapper.toResponse(si.getSeat()))
                .seatAvailabilityStatus(si.getStatus())
                .flightInstanceId(si.getFlightInstanceId())
                .flightCabinId(si.getFlightInstanceCabin() != null ? si.getFlightInstanceCabin().getId() : null)
                .fare(si.getFare())
                .price(si.getPremiumSurCharge())
                .version(si.getVersion())
                .createdAt(si.getCreatedAt())
                .updatedAt(si.getUpdatedAt())
                .isAvailable(si.getIsAvailable())
                .isBooked(si.getIsBooked())
                .isOccupied(si.getStatus() == SeatAvailabilityStatus.OCCUPIED)
                .build();
    }
}
