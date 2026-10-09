package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FlightInstanceCabinRequest;
import com.airflux.payload.response.FlightInstanceCabinResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.FlightInstanceCabin;
import com.airflux.seatService.entity.SeatMap;
import com.airflux.seatService.mapper.FlightInstanceCabinMapper;
import com.airflux.seatService.repository.CabinClassRepository;
import com.airflux.seatService.repository.FlightInstanceCabinRepository;
import com.airflux.seatService.repository.SeatMapRepository;
import com.airflux.seatService.service.FlightInstanceCabinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;

    public FlightInstanceCabinServiceImpl(CabinClassRepository cabinClassRepository, SeatMapRepository seatMapRepository, FlightInstanceCabinRepository flightInstanceCabinRepository) {
        this.cabinClassRepository = cabinClassRepository;
        this.seatMapRepository = seatMapRepository;
        this.flightInstanceCabinRepository = flightInstanceCabinRepository;
    }


    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) {

        CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId()).orElseThrow(
                () -> new ResourceNotFoundException("No cabin class found")
        );

        SeatMap seatMap = seatMapRepository.findByCabinClassId(request.getCabinClassId()).orElseThrow(
                () -> new ResourceNotFoundException("No seat map found")
        );

        if(seatMap.getSeats() == null || seatMap.getSeats().isEmpty()) {
            throw new ResourceNotFoundException("No seats found in seat map");
        }

        int totalSeats = seatMap.getSeats().size();

        FlightInstanceCabin fic = FlightInstanceCabinMapper
                .toEntity(request,cabinClass, totalSeats);

        FlightInstanceCabin saved = flightInstanceCabinRepository.save(fic);

        // todo: generate seat instance

        return FlightInstanceCabinMapper.toResponse(saved);
    }

    @Override
    public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No cabin found")
        );

        return FlightInstanceCabinMapper.toResponse(fic);
    }

    @Override
    public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long flightInstanceId, Pageable pageable) {

        return flightInstanceCabinRepository
                .findByFlightInstanceId(flightInstanceId, pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {

        FlightInstanceCabin fic = flightInstanceCabinRepository
                .findByFlightInstanceIdAndCabinClassId(
                        flightInstanceId,
                        cabinClassId
                );

        return FlightInstanceCabinMapper.toResponse(fic);
    }

    @Override
    public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No cabin found")
        );

        if(request.getCabinClassId() != null) {

            CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId()).orElseThrow(
                    () -> new ResourceNotFoundException("No cabin class found")
            );

            fic.setCabinClass(cabinClass);
        }

        FlightInstanceCabin updated = flightInstanceCabinRepository.save(fic);

        return FlightInstanceCabinMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightInstanceCabin(Long id) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No cabin found")
        );

        flightInstanceCabinRepository.delete(fic);
    }
}
