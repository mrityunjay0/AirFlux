package com.airflux.airlineCoreService.mapper;

import com.airflux.airlineCoreService.entity.Airline;
import com.airflux.payload.embeddable.Support;
import com.airflux.payload.request.AirlineRequest;
import com.airflux.payload.response.AirlineResponse;

public class AirlineMapper {

    public static Airline toEntity(AirlineRequest airlineRequest, Long ownerId) {

        if(airlineRequest == null) return null;

        Airline airline = Airline.builder()
                .iataCode(airlineRequest.getIataCode())
                .icaoCode(airlineRequest.getIcaoCode())
                .name(airlineRequest.getName())
                .alias(airlineRequest.getAlias())
                .logoUrl(airlineRequest.getLogoUrl())
                .website(airlineRequest.getWebsite())
                .status(airlineRequest.getStatus())
                .alliance(airlineRequest.getAlliance())
                .headQuaterCityId(airlineRequest.getHeadQuaterCityId())
                .ownerId(ownerId)
                .build();

        if(airlineRequest.getSupportEmail() != null
        || airlineRequest.getSupportPhone() != null
        || airlineRequest.getSupportHours() != null) {
            airline.setSupport(
                    Support.builder()
                            .email(airlineRequest.getSupportEmail())
                            .phone(airlineRequest.getSupportPhone())
                            .hours(airlineRequest.getSupportHours())
                            .build()
            );
        }

        return airline;
    }


    public static AirlineResponse toResponse(Airline airline) {

        if(airline == null) return null;

        AirlineResponse airlineResponse = AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();

        return airlineResponse;
    }

    public static void updateAirline(Airline airline, AirlineRequest airlineRequest) {

        if(airline == null || airlineRequest == null) return;

        airline.setIataCode(airlineRequest.getIataCode());
        airline.setIcaoCode(airlineRequest.getIcaoCode());
        airline.setName(airlineRequest.getName());
        airline.setAlias(airlineRequest.getAlias());
        airline.setLogoUrl(airlineRequest.getLogoUrl());
        airline.setWebsite(airlineRequest.getWebsite());
        airline.setStatus(airlineRequest.getStatus());
        airline.setAlliance(airlineRequest.getAlliance());
        airline.setHeadQuaterCityId(airlineRequest.getHeadQuaterCityId());

        if(airline.getSupport() == null) {
            airline.setSupport(new Support());
        }

        airline.getSupport().setEmail(airlineRequest.getSupportEmail());
        airline.getSupport().setPhone(airlineRequest.getSupportPhone());
        airline.getSupport().setHours(airlineRequest.getSupportHours());
    }

}
