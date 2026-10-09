package com.airflux.seatService.repository;

import com.airflux.seatService.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    boolean existsBySeatMapId(Long seatMapId);
}
