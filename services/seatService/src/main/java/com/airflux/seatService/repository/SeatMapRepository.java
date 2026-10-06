package com.airflux.seatService.repository;

import com.airflux.seatService.entity.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatMapRepository extends JpaRepository<SeatMap, Long> {

    SeatMap findByCabinClassId(Long cabinClassId);
}
