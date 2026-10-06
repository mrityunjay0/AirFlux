package com.airflux.seatService.service;

import com.airflux.payload.request.SeatMapRequest;
import com.airflux.payload.response.SeatMapResponse;

public interface SeatMapService {

    SeatMapResponse createSeatMap(Long userId, SeatMapRequest seatMapRequest);

    SeatMapResponse getSeatMapById(Long id);
    SeatMapResponse getSeatMapByCabinClass(Long cabinClassId);

    SeatMapResponse updateSeatMap(Long id, SeatMapRequest seatMapRequest);

    void deleteSeatMap(Long id);
}
