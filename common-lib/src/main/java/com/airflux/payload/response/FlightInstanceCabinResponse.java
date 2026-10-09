package com.airflux.payload.response;

import com.airflux.payload.enums.CabinClassType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInstanceCabinResponse {

    private Long id;
    private Long flightInstanceId;

    private CabinClassType cabinClassType;
    private CabinClassResponse cabinClassResponse;

    @Builder.Default
    private List<SeatInstanceResponse> seatInstanceResponseList = new ArrayList<>();

    @Builder.Default
    private SeatMapResponse seatMapResponse = new SeatMapResponse();

    private Integer totalSeats;
    private Integer bookedSeats;
    private Integer availableSeats;

    private Boolean isActive;
    private Boolean canBook;
}
