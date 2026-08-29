package com.airflux.locationService.repository;

import com.airflux.locationService.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findByIataCode(String iataCode);
    Airport findByCityId(Long cityId);

}
