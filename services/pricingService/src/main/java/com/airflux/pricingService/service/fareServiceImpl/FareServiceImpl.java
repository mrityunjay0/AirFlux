package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.FareResponse;
import com.airflux.pricingService.entity.Fare;
import com.airflux.pricingService.repository.FareRepository;
import com.airflux.pricingService.service.FareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    public FareServiceImpl(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }


    @Override
    public FareResponse createFare(FareRequest fareRequest) {
        return null;
    }

    @Override
    public FareResponse getFareById(Long fareId) {
        return null;
    }

    @Override
    public List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
        return List.of();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest fareRequest) {
        return null;
    }

    @Override
    public void deleteFareById(Long id) {

    }

    @Override
    public Map<Long, FareResponse> getLowestFaresPerFlight(List<Long> flightIds, Long cabinClassId) {
        return Map.of();
    }

    @Override
    public Map<Long, FareResponse> getFaresById(List<Long> fareIds) {
        return Map.of();
    }

    @Override
    public List<Fare> getAllFares() {
        return List.of();
    }
}
