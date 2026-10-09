package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.enums.SeatAvailabilityStatus;
import com.airflux.payload.enums.SeatType;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.FlightInstanceCabinRequest;
import com.airflux.payload.response.FlightInstanceCabinResponse;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.entity.FlightInstanceCabin;
import com.airflux.seatService.entity.SeatInstance;
import com.airflux.seatService.entity.SeatMap;
import com.airflux.seatService.mapper.FlightInstanceCabinMapper;
import com.airflux.seatService.repository.CabinClassRepository;
import com.airflux.seatService.repository.FlightInstanceCabinRepository;
import com.airflux.seatService.repository.SeatInstanceRepository;
import com.airflux.seatService.repository.SeatMapRepository;
import com.airflux.seatService.service.FlightInstanceCabinService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;
    private final SeatInstanceRepository seatInstanceRepository;
    private final SeatMapRepository seatMapRepository;

    public FlightInstanceCabinServiceImpl(CabinClassRepository cabinClassRepository, FlightInstanceCabinRepository flightInstanceCabinRepository, SeatInstanceRepository seatInstanceRepository, SeatMapRepository seatMapRepository) {
        this.cabinClassRepository = cabinClassRepository;
        this.flightInstanceCabinRepository = flightInstanceCabinRepository;
        this.seatInstanceRepository = seatInstanceRepository;
        this.seatMapRepository = seatMapRepository;
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

        // generate seat instance
        List<SeatInstance> seatInstances = seatMap.getSeats().stream()
                .map(seat -> {
                    Double premiumSurcharge = getPremiumSurcharge(
                            seat.getSeatType(),
                            1000.0,
                            500.0
                    );
                    return SeatInstance.builder()
                            .flightId(request.getFlightId())
                            .status(SeatAvailabilityStatus.AVAILABLE)
                            .flightInstanceId(request.getFlightInstanceId())
                            .flightInstanceCabin(saved)
                            .seat(seat)
                            .isAvailable(true)
                            .isBooked(false)
                            .premiumSurCharge(premiumSurcharge)
                            .build();
                })
                .toList();

        seatInstanceRepository.saveAll(seatInstances);
        saved.setSeatInstanceList(seatInstances);

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


    private Double getPremiumSurcharge(SeatType seatType,
                                       Double windowSurcharge,
                                       Double aisleSurcharge) {
        if (seatType == null) return 0.0;

        return switch (seatType) {
            case WINDOW -> windowSurcharge != null ? windowSurcharge : 0.0;
            case AISLE -> aisleSurcharge != null ? aisleSurcharge : 0.0;
            default -> 0.0;
        };
    }
}
