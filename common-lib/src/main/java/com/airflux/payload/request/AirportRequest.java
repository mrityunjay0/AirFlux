package com.airflux.payload.request;

import com.airflux.payload.embeddable.Address;
import com.airflux.payload.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AirportRequest {

    @NotBlank(message = "iata code is required.")
    @Size(min = 3, max = 3, message = "iata code must be of 3 characters")
    private String iataCode;

    @NotBlank(message = "Airport name is mandatory.")
    private String name;

    private String timeZone;

    @Valid
    private Address address;

    @NotNull(message = "City ID is mandatory.")
    private Long id;

    @Valid
    private GeoCode geoCode;

    public AirportRequest() {
    }

    public AirportRequest(String iataCode, String name, String timeZone, Address address, Long id, GeoCode geoCode) {
        this.iataCode = iataCode;
        this.name = name;
        this.timeZone = timeZone;
        this.address = address;
        this.id = id;
        this.geoCode = geoCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(GeoCode geoCode) {
        this.geoCode = geoCode;
    }
}
