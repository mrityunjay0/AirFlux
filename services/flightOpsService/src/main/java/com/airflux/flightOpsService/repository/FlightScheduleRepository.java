package com.airflux.flightOpsService.repository;

import com.airflux.flightOpsService.entity.FlightSchedule;
import com.airflux.payload.response.FlightScheduleResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {

    List<FlightSchedule> findByScheduledFlight_AirlineId(Long airlineId);
}
