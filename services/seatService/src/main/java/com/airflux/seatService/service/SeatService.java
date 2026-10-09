package com.airflux.seatService.service;

import com.airflux.payload.request.SeatRequest;
import com.airflux.payload.response.SeatResponse;

import java.util.List;

public interface SeatService {

    void generateSeats(Long seatMapId);

    // Only for dev but prod.
    List<SeatResponse> getAllSeats();
    SeatResponse getSeatById(Long seatId);

    SeatResponse updateSeat(Long id, SeatRequest seatRequest);
}
