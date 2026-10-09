package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.SeatMapRequest;
import com.airflux.payload.response.SeatMapResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.SeatMap;
import com.airflux.seatService.mapper.SeatMapMapper;
import com.airflux.seatService.repository.CabinClassRepository;
import com.airflux.seatService.repository.SeatMapRepository;
import com.airflux.seatService.service.SeatMapService;
import com.airflux.seatService.service.SeatService;
import org.springframework.stereotype.Service;

@Service
public class SeatMapServiceImpl implements SeatMapService {

    private final SeatMapRepository seatMapRepository;
    private final CabinClassRepository cabinClassRepository;
    private final SeatService seatService;

    public SeatMapServiceImpl(SeatMapRepository seatMapRepository, CabinClassRepository cabinClassRepository, SeatService seatService) {
        this.seatMapRepository = seatMapRepository;
        this.cabinClassRepository = cabinClassRepository;
        this.seatService = seatService;
    }


    @Override
    public SeatMapResponse createSeatMap(Long airlineId, SeatMapRequest seatMapRequest) {

        CabinClass cabinClass = cabinClassRepository.findById(seatMapRequest.getCabinClassId()).orElseThrow(
                () -> new ResourceNotFoundException("cabinClass with given Id not found")
        );

        if (seatMapRepository.existsByAirlineIdAndCabinClassIdAndName(
                airlineId,
                seatMapRequest.getCabinClassId(),
                seatMapRequest.getName() )
        ) {
            throw new DuplicateResourceException("Seat map already exists");
        }

        SeatMap seatMap = SeatMapMapper.toEntity(seatMapRequest, cabinClass, airlineId);
        SeatMap savedSeatMap = seatMapRepository.save(seatMap);

        // generating seats for seatMap
        seatService.generateSeats(savedSeatMap.getId());

        return SeatMapMapper.toResponse(savedSeatMap);
    }

    @Override
    public SeatMapResponse getSeatMapById(Long id) {

        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Seat map with given Id not found")
        );

        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse getSeatMapByCabinClass(Long cabinClassId) {

        SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClassId).orElseThrow(
                () -> new ResourceNotFoundException("Seat map with given cabin class Id not found")
        );

        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse updateSeatMap(Long id, SeatMapRequest seatMapRequest) {

        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Seat map with given Id not found")
        );

        SeatMapMapper.updateEntity(seatMap, seatMapRequest);
        SeatMap savedSeatMap = seatMapRepository.save(seatMap);

        return SeatMapMapper.toResponse(savedSeatMap);
    }

    @Override
    public void deleteSeatMap(Long id) {

        SeatMap seatMap = seatMapRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Seat map with given Id not found")
        );

        seatMapRepository.delete(seatMap);
    }
}
