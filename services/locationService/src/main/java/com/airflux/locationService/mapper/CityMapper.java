package com.airflux.locationService.mapper;

import com.airflux.locationService.entity.City;
import com.airflux.payload.request.CityRequest;
import com.airflux.payload.response.CityResponse;

public class CityMapper {

    public static City toEntity(CityRequest cityRequest) {

        if(cityRequest == null) return null;

        return City.builder()
                .name(cityRequest.getName())
                .cityCode(cityRequest.getCityCode())
                .countryCode(cityRequest.getCountryCode())
                .countryName(cityRequest.getCountryName())
                .regionCode(cityRequest.getRegionCode())
                .timeZone(cityRequest.getTimeZone())
                .build();
    }

    public static CityResponse toResponse(City city) {

        if(city == null) return null;

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryCode(city.getCountryCode())
                .countryName(city.getCountryName())
                .regionCode(city.getRegionCode())
//                .timeZone(city.getTimeZone())
                .build();
    }

    public static City updateEntity(City city, CityRequest cityRequest) {

        if(cityRequest.getName() != null){
            city.setName(cityRequest.getName().trim());
        }

        if(cityRequest.getCityCode() != null){
            city.setCityCode(cityRequest.getCityCode().toUpperCase().trim());
        }

        if(cityRequest.getCountryCode() != null){
            city.setCountryCode(cityRequest.getCountryCode().toUpperCase().trim());
        }

        if(cityRequest.getCountryName() != null){
            city.setCountryName(cityRequest.getCountryName().trim());
        }

        if(cityRequest.getRegionCode() != null){
            city.setRegionCode(cityRequest.getRegionCode().toUpperCase().trim());
        }

        return city;
    }
}
