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

    public static Airport updateEntity(Airport airport,AirportRequest airportRequest) {

        if(airport == null || airportRequest == null) return null;

        if(airportRequest.getIataCode() != null) {
            airport.setIataCode(airportRequest.getIataCode());
        }

        if(airportRequest.getName() != null) {
            airport.setName(airportRequest.getName());
        }

        if(airportRequest.getTimeZone() != null) {
            airport.setTimeZoneId(airportRequest.getTimeZone());
        }

        if(airportRequest.getAddress() != null) {
            airport.setAddress(airportRequest.getAddress());
        }

        if(airportRequest.getGeoCode() != null) {
            airport.setGeoCode(airportRequest.getGeoCode());
        }

        return airport;
    }
}
