package com.airflux.airlineCoreService.mapper;

import com.airflux.airlineCoreService.entity.Aircraft;
import com.airflux.airlineCoreService.entity.Airline;
import com.airflux.payload.request.AircraftRequest;
import com.airflux.payload.response.AircraftResponse;

public class AircraftMapper {

    public static Aircraft toEntity(AircraftRequest aircraftRequest, Airline airline) {

        if(aircraftRequest == null) return null;

        return Aircraft.builder()
                .code(aircraftRequest.getCode())
                .model(aircraftRequest.getModel())
                .manufacturer(aircraftRequest.getManufacturer())
                .seatingCapacity(aircraftRequest.getSeatingCapacity())
                .economySeats(aircraftRequest.getEconomySeats())
                .premiumEconomySeats(aircraftRequest.getPremiumEconomySeats())
                .businessSeats(aircraftRequest.getBusinessSeats())
                .firstClassSeats(aircraftRequest.getFirstClassSeats())
                .rangeKm(aircraftRequest.getRangeKm())
                .cruisingSpeedKmh(aircraftRequest.getCruisingSpeedKmh())
                .maxAltitude(aircraftRequest.getMaxAltitude())
                .yearOfManufacture(aircraftRequest.getYearOfManufacture())
                .registrationDate(aircraftRequest.getRegistrationDate())
                .nextMaintenanceDate(aircraftRequest.getNextMaintenanceDate())
                .aircraftStatus(aircraftRequest.getStatus())
                .isAvailable(aircraftRequest.getIsAvailable())
                .airline(airline)
                .currentAirportId(aircraftRequest.getCurrentAirportId())
                .build();
    }

    public static AircraftResponse toResponse(Aircraft aircraft) {

        if(aircraft == null) return null;

        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .premiumEconomySeats(aircraft.getPremiumEconomySeats())
                .businessSeats(aircraft.getBusinessSeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitude())
                .yearOfManufacture(aircraft.getYearOfManufacture())
                .registrationDate(aircraft.getRegistrationDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getAircraftStatus())
                .isAvailable(aircraft.getIsAvailable())
                // Airline Informantion
                .airlineId(aircraft.getAirline() != null ? aircraft.getAirline().getId() : null)
                .airlineName(aircraft.getAirline() != null ? aircraft.getAirline().getName() : null)
                .airlineIataCode(aircraft.getAirline() != null ? aircraft.getAirline().getIataCode() : null)
                // Airport is cross service, only id is available here
                .currentAirportId(aircraft.getCurrentAirportId())
                // Computed seats
                .totalSeats(aircraft.getTotalSeats())
                .requiresMaintenance(aircraft.requiresMaintenance())
                .isOperational(aircraft.isOperational())
                // Audit
                .createdAt(aircraft.getCreatedAt())
                .updatedAt(aircraft.getUpdatedAt())
                .build();
    }

    public static void updateAircraft(Aircraft aircraft, AircraftRequest aircraftRequest) {

        if(aircraft == null || aircraftRequest == null) return;

        aircraft.setCode(aircraftRequest.getCode());
        aircraft.setModel(aircraftRequest.getModel());
        aircraft.setManufacturer(aircraftRequest.getManufacturer());
        aircraft.setSeatingCapacity(aircraftRequest.getSeatingCapacity());
        aircraft.setEconomySeats(aircraftRequest.getEconomySeats());
        aircraft.setPremiumEconomySeats(aircraftRequest.getPremiumEconomySeats());
        aircraft.setBusinessSeats(aircraftRequest.getBusinessSeats());
        aircraft.setFirstClassSeats(aircraftRequest.getFirstClassSeats());
        aircraft.setRangeKm(aircraftRequest.getRangeKm());
        aircraft.setCruisingSpeedKmh(aircraftRequest.getCruisingSpeedKmh());
        aircraft.setMaxAltitude(aircraftRequest.getMaxAltitude());
        aircraft.setYearOfManufacture(aircraftRequest.getYearOfManufacture());
        aircraft.setRegistrationDate(aircraftRequest.getRegistrationDate());
        aircraft.setNextMaintenanceDate(aircraftRequest.getNextMaintenanceDate());
        aircraft.setAircraftStatus(aircraftRequest.getStatus());
        aircraft.setIsAvailable(aircraftRequest.getIsAvailable());
        aircraft.setCurrentAirportId(aircraftRequest.getCurrentAirportId());
    }
}
