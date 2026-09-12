package com.airflux.locationService.service.impl;

import com.airflux.locationService.entity.City;
import com.airflux.locationService.mapper.CityMapper;
import com.airflux.locationService.repository.CityRepository;
import com.airflux.locationService.service.CityService;
import com.airflux.payload.exception.DuplicateResourceException;
import com.airflux.payload.exception.ResourceNotFoundException;
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
    public CityResponse createCity(CityRequest cityRequest) {

        if(cityRepository.existsByCityCode(cityRequest.getCityCode())){
            throw new DuplicateResourceException("City with given code: " + cityRequest.getCityCode() + " already exists.");
        }

        City city = CityMapper.toEntity(cityRequest);
        City savedCity = cityRepository.save(city);

        return CityMapper.toResponse(savedCity);

    }

    @Override
    public CityResponse getCityById(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new ResourceNotFoundException("City not exists with ID:" + cityId)
        );

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long cityId, CityRequest cityRequest) {

        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new ResourceNotFoundException("City not exists with ID:" + cityId)
        );

        if(cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(),cityId)){
            throw new DuplicateResourceException("City with given code: " + cityRequest.getCityCode() + " already exists.");
        }

        City updatedCity = cityRepository.save(CityMapper.updateEntity(city,cityRequest));
        return CityMapper.toResponse(updatedCity);

    }

    @Override
    public void deleteCity(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new ResourceNotFoundException("City not exists with ID:" + cityId)
        );

        cityRepository.delete(city);

    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {

        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCitiesByKeyword(String keyword, Pageable pageable) {

        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {

        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {

        return cityRepository.existsByCityCode(cityCode);
    }

}
