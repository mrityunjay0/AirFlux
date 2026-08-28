package com.airflux.locationService.service;

import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    CityResponse createCity(CityRequest cityRequest) throws Exception;
    CityResponse getCityById(Long cityId) throws Exception;
    CityResponse updateCity(Long cityId, CityRequest cityRequest) throws Exception;

    void deleteCity(Long cityId);

    Page<CityResponse> getAllCities(Pageable pageable);
    Page<CityResponse> searchCities(String keyword, Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode);

    boolean cityExists(String cityCode);
    boolean validateCityCode(String cityCode);

}
