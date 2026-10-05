package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.request.BaggagePolicyRequest;
import com.airflux.payload.response.BaggagePolicyResponse;
import com.airflux.pricingService.repository.BaggagePolicyRepository;
import com.airflux.pricingService.service.BaggagePolicyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

    private final BaggagePolicyRepository baggagePolicyRepository;

    public BaggagePolicyServiceImpl(BaggagePolicyRepository baggagePolicyRepository) {
        this.baggagePolicyRepository = baggagePolicyRepository;
    }

    
    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicyRequest) {
        return null;
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long id) {
        return null;
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
        return null;
    }

    @Override
    public List<BaggagePolicyResponse> getBaggagePolicyByAirlineId(Long airlineId) {
        return List.of();
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest baggagePolicyRequest) {
        return null;
    }

    @Override
    public void deleteBaggagePolicy(Long id) {

    }
}
