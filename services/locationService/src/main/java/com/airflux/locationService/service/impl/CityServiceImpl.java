package com.airflux.locationService.service.impl;

import com.airflux.locationService.entity.City;
import com.airflux.locationService.mapper.CityMapper;
import com.airflux.locationService.repository.CityRepository;
import com.airflux.locationService.service.CityService;
import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public CityResponse createCity(CityRequest cityRequest) throws Exception {

        if(cityRepository.existsByCityCode(cityRequest.getCityCode())){
            throw new Exception("City with given code already exists.");
        }

        City city = CityMapper.toEntity(cityRequest);
        City savedCity = cityRepository.save(city);

        return CityMapper.toResponse(savedCity);

    }

    @Override
    public CityResponse getCityById(Long cityId) throws Exception {

        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new Exception("City not exists with given ID.")
        );

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long cityId, CityRequest cityRequest) throws Exception {

        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new Exception("City not exists with given ID.")
        );

        if(cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(),cityId)){
            throw new Exception("City with given code already exists.");
        }

        City updatedCity = cityRepository.save(CityMapper.updateEntity(city,cityRequest));
        return CityMapper.toResponse(updatedCity);
        
    }

    @Override
    public void deleteCity(Long cityId) {

    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode) {
        return null;
    }

    @Override
    public boolean cityExists(String cityCode) {
        return false;
    }

    @Override
    public boolean validateCityCode(String cityCode) {
        return false;
    }
}
