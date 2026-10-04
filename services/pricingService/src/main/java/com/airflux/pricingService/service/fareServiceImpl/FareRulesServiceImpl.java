package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.request.FareRulesRequest;
import com.airflux.payload.response.FareRulesResponse;
import com.airflux.pricingService.repository.FareRulesRepository;
import com.airflux.pricingService.service.FareRulesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareRulesServiceImpl implements FareRulesService {

    private final FareRulesRepository fareRulesRepository;

    public FareRulesServiceImpl(FareRulesRepository fareRulesRepository) {
        this.fareRulesRepository = fareRulesRepository;
    }


    @Override
    public FareRulesResponse createFareRules(FareRulesRequest fareRulesRequest) {
        return null;
    }

    @Override
    public FareRulesResponse getFareRulesById(Long fareRulesId) {
        return null;
    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {
        return null;
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {
        return List.of();
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest fareRulesRequest) {
        return null;
    }

    @Override
    public void deleteFareRules(Long fareRulesId) {

    }
}
