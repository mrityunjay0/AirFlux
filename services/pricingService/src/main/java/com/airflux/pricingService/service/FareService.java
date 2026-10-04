package com.airflux.pricingService.service;

import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.FareResponse;
import com.airflux.pricingService.entity.Fare;

import java.util.List;
import java.util.Map;

public interface FareService {

    FareResponse createFare(FareRequest fareRequest);

    FareResponse getFareById(Long fareId);
    List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId);

    FareResponse updateFare(Long id, FareRequest fareRequest);

    void deleteFareById(Long id);

    Map<Long, FareResponse> getLowestFaresPerFlight(
            List<Long> flightIds, Long cabinClassId
    );
    Map<Long, FareResponse> getFaresById(List<Long> fareIds);

    // Only for Development and Testing purposes, not for production use
    List<Fare> getAllFares();

}
