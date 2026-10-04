package com.airflux.pricingService.service.fareServiceImpl;

import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FareRequest;
import com.airflux.payload.response.FareResponse;
import com.airflux.pricingService.entity.Fare;
import com.airflux.pricingService.mapper.FareMapper;
import com.airflux.pricingService.repository.FareRepository;
import com.airflux.pricingService.service.FareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    public FareServiceImpl(FareRepository fareRepository) {
        this.fareRepository = fareRepository;
    }


    @Override
    public FareResponse createFare(FareRequest fareRequest) {

        if(fareRepository.existsByFlightIdAndCabinClassIdAndName(
                fareRequest.getFlightId(),
                fareRequest.getCabinClassId(),
                fareRequest.getName()
        )) {
            throw new DuplicateResourceException("Fare already exists for the given flight and cabin class");
        }

        Fare fare = FareMapper.toEntity(fareRequest);
        Fare savedFare = fareRepository.save(fare);

        return FareMapper.toResponse(savedFare);
    }

    @Override
    public FareResponse getFareById(Long fareId) {

        Fare fare = fareRepository.findById(fareId).orElseThrow(
                () -> new ResourceNotFoundException("Fare not found with id: " + fareId)
        );

        return FareMapper.toResponse(fare);
    }

    @Override
    public List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {

        List<Fare> fares = fareRepository.findByFlightIdAndCabinClassId(flightId, cabinClassId);

        return fares.stream()
                .map(FareMapper::toResponse)
                .toList();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest fareRequest) {

        Fare existingFare = fareRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fare not found with id: " + id)
        );

        if(fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(
                fareRequest.getFlightId(),
                fareRequest.getCabinClassId(),
                fareRequest.getName(),
                id
        )) {
            throw new DuplicateResourceException("Fare already exists for the given flight and cabin class");
        }


        FareMapper.updateEntity(existingFare, fareRequest);

        return FareMapper.toResponse(fareRepository.save(existingFare));
    }

    @Override
    public void deleteFareById(Long id) {

        Fare fare = fareRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Fare not found with id: " + id)
        );

        fareRepository.delete(fare);
    }

    @Override
    public Map<Long, FareResponse> getLowestFaresPerFlight(List<Long> flightIds,
                                                           Long cabinClassId) {

        if (flightIds == null || flightIds.isEmpty()) {
            return Map.of();
        }

        List<Fare> fares = fareRepository.findByFlightIdAndCabinClassId(
                flightIds,
                cabinClassId
        );

        Map<Long, FareResponse> result = fares.stream()
                .collect(Collectors.toMap(
                        Fare::getFlightId,
                        fare -> fare,
                        (existing, candidate) ->
                                existing.getTotalPrice()
                                        .compareTo(candidate.getTotalPrice()) <= 0
                                        ? existing
                                        : candidate
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> FareMapper.toResponse(entry.getValue())
                ));

        return result;
    }

    @Override
    public Map<Long, FareResponse> getFaresById(List<Long> fareIds) {

        List<Fare> fares = fareRepository.findAllById(fareIds);

        return fares.stream()
                .collect(
                        Collectors.toMap(
                                Fare::getId,
                                FareMapper::toResponse
                        )
                );
    }

    @Override
    public List<Fare> getAllFares() {

        return fareRepository.findAll();
    }
}
