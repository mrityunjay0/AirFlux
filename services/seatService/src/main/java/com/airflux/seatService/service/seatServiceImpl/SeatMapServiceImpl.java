package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.request.SeatMapRequest;
import com.airflux.payload.response.SeatMapResponse;
import com.airflux.seatService.repository.SeatMapRepository;
import com.airflux.seatService.service.SeatMapService;
import org.springframework.stereotype.Service;

@Service
public class SeatMapServiceImpl implements SeatMapService {

    private final SeatMapRepository seatMapRepository;

    public SeatMapServiceImpl(SeatMapRepository seatMapRepository) {
        this.seatMapRepository = seatMapRepository;
    }



    @Override
    public SeatMapResponse createSeatMap(Long userId, SeatMapRequest seatMapRequest) {
        return null;
    }

    @Override
    public SeatMapResponse getSeatMapById(Long id) {
        return null;
    }

    @Override
    public SeatMapResponse getSeatMapByCabinClass(Long cabinClassId) {
        return null;
    }

    @Override
    public SeatMapResponse updateSeatMap(Long id, SeatMapRequest seatMapRequest) {
        return null;
    }

    @Override
    public void deleteSeatMap(Long id) {

    }
}
