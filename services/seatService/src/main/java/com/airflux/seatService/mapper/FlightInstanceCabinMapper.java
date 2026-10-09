package com.airflux.seatService.mapper;

import com.airflux.payload.request.FlightInstanceCabinRequest;
import com.airflux.payload.response.FlightInstanceCabinResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.FlightInstanceCabin;


public class FlightInstanceCabinMapper {

    public static FlightInstanceCabin toEntity(FlightInstanceCabinRequest request,
                                               CabinClass cabinClass, int totalSeats) {

        if(request == null) return null;

        return FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeats)
                .bookedSeats(0)
                .build();
    }

    public static FlightInstanceCabinResponse toResponse(FlightInstanceCabin fic) {

        if (fic == null) return null;

        return FlightInstanceCabinResponse.builder()
                .id(fic.getId())
                .flightInstanceId(fic.getFlightInstanceId())
                .cabinClassType(fic.getCabinClass() != null ? fic.getCabinClass().getType() : null)

                .cabinClassResponse(fic.getCabinClass() != null ?
                        CabinClassMapper.toResponse(fic.getCabinClass()) : null)

                // todo: seatInstance
//                .seats(fic.getSeats() != null ?
//                        fic.getSeats().stream().map(SeatInstanceMapper::toResponse)
//                                .collect(Collectors.toList()) : null)

                .seatMapResponse(fic.getCabinClass() != null && fic.getCabinClass().getSeatMap() != null ?
                        SeatMapMapper.toSimpleResponse(fic.getCabinClass().getSeatMap()) : null)

                .totalSeats(fic.getTotalSeats())
                .bookedSeats(fic.getBookedSeats())
                .availableSeats(fic.getTotalSeats() - fic.getBookedSeats())
                .build();
    }

}
