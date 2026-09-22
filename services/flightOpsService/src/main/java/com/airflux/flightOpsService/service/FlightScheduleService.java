package com.airflux.flightOpsService.service;

import com.airflux.payload.request.FlightScheduleRequest;
import com.airflux.payload.response.FlightScheduleResponse;

import java.util.List;

public interface FlightScheduleService {

    FlightScheduleResponse createFlightSchedule(Long airlineId, FlightScheduleRequest flightScheduleRequest);

    FlightScheduleResponse getFlightScheduleById(Long flightScheduleId);

    List<FlightScheduleResponse> getFlightScheduleByAirline(Long userId);

    FlightScheduleResponse updateFlightSchedule(Long flightScheduleId, FlightScheduleRequest flightScheduleRequest);

    void deleteFlightScheduleById(Long flightScheduleId);
}
