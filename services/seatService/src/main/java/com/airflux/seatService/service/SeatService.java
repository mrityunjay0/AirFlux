package com.airflux.seatService.service;

import com.airflux.payload.request.SeatRequest;
import com.airflux.payload.response.SeatResponse;

public interface SeatService {

    void generateSeats(Long seatMapId);

    SeatResponse createSeat(SeatRequest seatRequest);
}
