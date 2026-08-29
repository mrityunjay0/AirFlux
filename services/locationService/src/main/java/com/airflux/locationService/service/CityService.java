package com.airflux.locationService.service;

import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    CityResponse createCity(CityRequest cityRequest) throws Exception;
    CityResponse getCityById(Long cityId) throws Exception;
    CityResponse updateCity(Long cityId, CityRequest cityRequest) throws Exception;

    void deleteCity(Long cityId) throws Exception;

    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCitiesByKeyword(String keyword, Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

    boolean cityExists(String cityCode);

}
