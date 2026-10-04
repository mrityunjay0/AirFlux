package com.airflux.pricingService.service;

import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.FareResponse;

public interface FareService {

    FareResponse createFare(FareRequest fareRequest);
}
