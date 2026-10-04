package com.airflux.pricingService.service;

import com.airflux.payload.request.FareRulesRequest;
import com.airflux.payload.response.FareRulesResponse;

import java.util.List;

public interface FareRulesService {

    FareRulesResponse createFareRules(FareRulesRequest fareRulesRequest);

    FareRulesResponse getFareRulesById(Long fareRulesId);
    FareRulesResponse getFareRulesByFareId(Long fareId);
    List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId);

    FareRulesResponse updateFareRules(Long id, FareRulesRequest fareRulesRequest);

    void deleteFareRules(Long fareRulesId);
}
