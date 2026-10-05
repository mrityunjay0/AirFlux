package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.BaggagePolicyRequest;
import com.airflux.payload.response.BaggagePolicyResponse;
import com.airflux.pricingService.entity.BaggagePolicy;
import com.airflux.pricingService.entity.Fare;
import com.airflux.pricingService.mapper.BaggagePolicyMapper;
import com.airflux.pricingService.repository.BaggagePolicyRepository;
import com.airflux.pricingService.repository.FareRepository;
import com.airflux.pricingService.service.BaggagePolicyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaggagePolicyServiceImpl implements BaggagePolicyService {

    private final BaggagePolicyRepository baggagePolicyRepository;
    private final FareRepository fareRepository;

    public BaggagePolicyServiceImpl(BaggagePolicyRepository baggagePolicyRepository, FareRepository fareRepository) {
        this.baggagePolicyRepository = baggagePolicyRepository;
        this.fareRepository = fareRepository;
    }


    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest baggagePolicyRequest) {

        Fare fare = fareRepository.findById(baggagePolicyRequest.getFareId()).orElseThrow(
                () -> new ResourceNotFoundException("Fare does not exists with given fareId: "
                        + baggagePolicyRequest.getFareId())
        );

        if(baggagePolicyRepository.existsByFareId(baggagePolicyRequest.getFareId())) {
            throw new DuplicateResourceException("Baggage Policy already exists");
        }

        BaggagePolicy baggagePolicy = BaggagePolicyMapper.toEntity(baggagePolicyRequest, fare);
        baggagePolicyRepository.save(baggagePolicy);

        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long id) {

        BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Baggage Policy not found with given id")
        );

        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {

        BaggagePolicy baggagePolicy = baggagePolicyRepository.findByFareId(fareId).orElseThrow(
                () -> new ResourceNotFoundException("Baggage Policy not found with given fareId")
        );

        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public List<BaggagePolicyResponse> getBaggagePolicyByAirlineId(Long airlineId) {

        return baggagePolicyRepository.findByAirlineId(airlineId).stream()
                .map(BaggagePolicyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest baggagePolicyRequest) {

        BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Baggage Policy not found with given id")
        );

        BaggagePolicyMapper.updateEntity(baggagePolicyRequest, baggagePolicy);
        baggagePolicyRepository.save(baggagePolicy);

        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public void deleteBaggagePolicy(Long id) {

        BaggagePolicy baggagePolicy = baggagePolicyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Baggage Policy not found with given id")
        );

        baggagePolicyRepository.deleteById(baggagePolicy.getId());
    }
}
