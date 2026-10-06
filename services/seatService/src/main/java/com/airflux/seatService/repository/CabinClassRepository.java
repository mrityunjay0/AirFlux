package com.airflux.seatService.repository;

import com.airflux.payload.enums.CabinClassType;
import com.airflux.seatService.entity.CabinClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CabinClassRepository extends JpaRepository<CabinClass, Long> {

    List<CabinClass> findByAircraftId(Long aircraftId);
    Optional<CabinClass> findByAircraftIdAndType(Long aircraftId, CabinClassType type);
    boolean existsByAircraftIdAndCode(Long aircraftId, String code);
    boolean existsByCodeAndAircraftIdAndIdNot(String code, Long aircraftId, Long id);
}
