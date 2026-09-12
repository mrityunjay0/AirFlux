package com.airflux.locationService.service;

import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    CityResponse createCity(CityRequest cityRequest);
    CityResponse getCityById(Long cityId);
    CityResponse updateCity(Long cityId, CityRequest cityRequest);

    void deleteCity(Long cityId);

    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCitiesByKeyword(String keyword, Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

    boolean cityExists(String cityCode);

}
