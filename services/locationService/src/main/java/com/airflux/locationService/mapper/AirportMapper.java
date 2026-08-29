package com.airflux.locationService.mapper;

import com.airflux.locationService.entity.Airport;
import com.airflux.payload.request.AirportRequest;
import com.airflux.payload.response.AirportResponse;

public class AirportMapper {

    public static Airport toEntity(AirportRequest airportRequest) {

        if(airportRequest == null) return null;

        return Airport.builder()
                .iataCode(airportRequest.getIataCode())
                .name(airportRequest.getName())
                .address(airportRequest.getAddress())
                .geoCode(airportRequest.getGeoCode())
                .timeZoneId(airportRequest.getTimeZone())
                .build();
    }

    public static AirportResponse toResponse(Airport airport) {

        if(airport == null) return null;

        return AirportResponse.builder()
                .id(airport.getId())
                .iataCode(airport.getIataCode())
                .name(airport.getName())
                .detailedName(airport.getDetailedName())
                .timeZone(airport.getTimeZoneId())
                .address(airport.getAddress())
                .city(CityMapper.toResponse(airport.getCity()))
                .geoCode(airport.getGeoCode())
                .build();
    }
}
