package com.airflux.locationService.service;

import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;

public interface CityService {

    CityResponse createCity(CityRequest cityRequest);
}
