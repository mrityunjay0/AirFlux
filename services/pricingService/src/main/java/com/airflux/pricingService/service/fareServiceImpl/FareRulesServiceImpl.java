package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FareRulesRequest;
import com.airflux.payload.response.FareRulesResponse;
import com.airflux.pricingService.entity.Fare;
import com.airflux.pricingService.entity.FareRules;
import com.airflux.pricingService.mapper.FareRulesMapper;
import com.airflux.pricingService.repository.FareRepository;
import com.airflux.pricingService.repository.FareRulesRepository;
import com.airflux.pricingService.service.FareRulesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FareRulesServiceImpl implements FareRulesService {

    private final FareRulesRepository fareRulesRepository;
    private final FareRepository fareRepository;

    public FareRulesServiceImpl(FareRulesRepository fareRulesRepository, FareRepository fareRepository) {
        this.fareRulesRepository = fareRulesRepository;
        this.fareRepository = fareRepository;
    }


    @Override
    public FareRulesResponse createFareRules(FareRulesRequest fareRulesRequest) {

        Fare fare = fareRepository.findById(fareRulesRequest.getFareId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Fare not found with id: " + fareRulesRequest.getFareId()
                )
        );

        if(fareRulesRepository.existsByFareId(fareRulesRequest.getFareId())) {
            throw new DuplicateResourceException(
                    "FareRules already exists for fareId: " + fareRulesRequest.getFareId()
            );
        }

        FareRules fareRules = FareRulesMapper.toEntity(fareRulesRequest, fare);
        fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long fareRulesId) {

        FareRules fareRules = fareRulesRepository.findById(fareRulesId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "FareRules not found with id: " + fareRulesId
                )
        );

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) {

        FareRules fareRules = fareRulesRepository.getByFareId(fareId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Fare rule not found with id: " + fareId
                )
        );

        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {

        return fareRulesRepository.findByAirlineId(airlineId).stream()
                .map(FareRulesMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FareRulesResponse updateFareRules(Long id, FareRulesRequest fareRulesRequest) {

        Fare fare = fareRepository.findById(fareRulesRequest.getFareId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Fare not found with given fareId."
                )
        );

        FareRules fareRules = fareRulesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Fare Rules not found with given id."
                )
        );

        FareRulesMapper.updateEntity(fareRules, fareRulesRequest, fare);
        fareRulesRepository.save(fareRules);

        return FareRulesMapper.toResponse(fareRules);

    }

    @Override
    public void deleteFareRules(Long fareRulesId) {

        FareRules fareRules = fareRulesRepository.findById(fareRulesId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Fare Rule not found with given id."
                )
        );

        fareRulesRepository.delete(fareRules);
    }
}
