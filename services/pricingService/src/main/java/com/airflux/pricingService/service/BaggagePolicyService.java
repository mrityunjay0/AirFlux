package com.airflux.pricingService.service;

import com.airflux.payload.request.BaggagePolicyRequest;
import com.airflux.payload.response.BaggagePolicyResponse;

import java.util.List;

public interface BaggagePolicyService {

    BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicyRequest);

    BaggagePolicyResponse getBaggagePolicyById(Long id);
    BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId);
    List<BaggagePolicyResponse> getBaggagePolicyByAirlineId(Long airlineId);

    BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest baggagePolicyRequest);

    void deleteBaggagePolicy(Long id);


}
