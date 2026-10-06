package com.airflux.seatService.service.seatServiceImpl;

import com.airflux.payload.enums.CabinClassType;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.payload.request.CabinClassRequest;
import com.airflux.payload.response.CabinClassResponse;
import com.airflux.seatService.repository.CabinClassRepository;
import com.airflux.seatService.entity.CabinClass;
import com.airflux.seatService.mapper.CabinClassMapper;
import com.airflux.seatService.service.CabinClassService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CabinClassServiceImpl implements CabinClassService {

    private final CabinClassRepository cabinClassRepository;

    public CabinClassServiceImpl(CabinClassRepository cabinClassRepository) {
        this.cabinClassRepository = cabinClassRepository;
    }



    @Override
    public CabinClassResponse createCabinClass(CabinClassRequest cabinClassRequest) {

        if (cabinClassRepository.existsByAircraftIdAndCode(
                cabinClassRequest.getAircraftId(), cabinClassRequest.getCode()
        )){
            throw new DuplicateResourceException("Cabin class already exists with given code");
        }

        CabinClass cabinClass = CabinClassMapper.toEntity(cabinClassRequest);
        CabinClass saved = cabinClassRepository.save(cabinClass);

        return CabinClassMapper.toResponse(saved);
    }

    @Override
    public CabinClassResponse getCabinClassById(Long id) {

        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cabin class not found with given id")
        );

        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public List<CabinClassResponse> getCabinClassesByAircraftId(Long aircraftId) {

        return cabinClassRepository.findByAircraftId(aircraftId).stream()
                .map(CabinClassMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CabinClassResponse getCabinClassByAircraftIdAndType(Long aircraftId, CabinClassType type) {

        CabinClass cabinClass = cabinClassRepository.findByAircraftIdAndType(aircraftId, type).orElseThrow(
                () -> new ResourceNotFoundException("Cabin class not found with given id and type")
        );

        return CabinClassMapper.toResponse(cabinClass);
    }

    @Override
    public CabinClassResponse updateCabinClass(Long id, CabinClassRequest cabinClassRequest) {

        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cabin class not found with given id")
        );

        if (cabinClassRepository.existsByCodeAndAircraftIdAndIdNot(
                cabinClassRequest.getCode().toUpperCase(),
                cabinClassRequest.getAircraftId(),
                cabinClass.getId()
        )){
            throw new DuplicateResourceException("Cabin class already exists with given code");
        }

        CabinClassMapper.updateEntity(cabinClass, cabinClassRequest);
        CabinClass updated = cabinClassRepository.save(cabinClass);

        return CabinClassMapper.toResponse(updated);
    }

    @Override
    public void deleteCabinClassById(Long id) {

        CabinClass cabinClass = cabinClassRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cabin class not found with given id")
        );

        cabinClassRepository.delete(cabinClass);
    }
}
