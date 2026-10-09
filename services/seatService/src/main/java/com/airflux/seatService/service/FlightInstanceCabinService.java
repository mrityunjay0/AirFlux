package com.airflux.seatService.service;

import com.airflux.payload.request.FlightInstanceCabinRequest;
import com.airflux.payload.response.FlightInstanceCabinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightInstanceCabinService {

    FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request);

    FlightInstanceCabinResponse getFlightInstanceCabinById(Long id);
    Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long flightInstanceId,
                                                            Pageable pageable);
    FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId);

    FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request);

    void deleteFlightInstanceCabin(Long id);
}
