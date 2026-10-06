package com.airflux.seatService.service;

import com.airflux.payload.enums.CabinClassType;
import com.airflux.payload.request.CabinClassRequest;
import com.airflux.payload.response.CabinClassResponse;

import java.util.List;

public interface CabinClassService {

    CabinClassResponse createCabinClass(CabinClassRequest cabinClassRequest);

    CabinClassResponse getCabinClassById(Long id);
    List<CabinClassResponse> getCabinClassesByAircraftId(Long aircraftId);
    CabinClassResponse getCabinClassByAircraftIdAndType(Long aircraftId, CabinClassType type);

    CabinClassResponse updateCabinClass(Long id, CabinClassRequest cabinClassRequest);

    void deleteCabinClassById(Long id);
}
