package com.airflux.seatService.repository;

import com.airflux.seatService.entity.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatMapRepository extends JpaRepository<SeatMap, Long> {

    Optional<SeatMap> findByCabinClassId(Long cabinClassId);

    boolean existsByAirlineIdAndCabinClassIdAndName(Long airlineId,
                                                    Long cabinClassId,
                                                    String name);
}
