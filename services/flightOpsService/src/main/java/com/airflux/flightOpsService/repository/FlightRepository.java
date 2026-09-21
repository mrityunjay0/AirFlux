package com.airflux.flightOpsService.repository;

import com.airflux.flightOpsService.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Page<Flight> findByAirlineId(Long airlineId, Pageable pageable);
    boolean existsByFlightNumber(String flightNumber);
}
