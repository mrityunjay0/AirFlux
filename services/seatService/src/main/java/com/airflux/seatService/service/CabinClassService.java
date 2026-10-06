package com.airflux.seatService.service;

import com.airflux.payload.response.CabinClassResponse;

public interface CabinClassService {

    CabinClassResponse createCabinClass(CabinClassRequest cabinClassRequest);
}
