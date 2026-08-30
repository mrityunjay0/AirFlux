package com.airflux.locationService.repository;

import com.airflux.locationService.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByIataCode(String iataCode);
    Airport findByCityId(Long cityId);

}
