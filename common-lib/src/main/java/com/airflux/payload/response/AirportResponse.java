package com.airflux.payload.response;

import com.airflux.payload.embeddable.Address;
import com.airflux.payload.embeddable.GeoCode;
import lombok.Builder;


@Builder
public class AirportResponse {

    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private String timeZone;
    private Address address;
    private CityResponse city;
    private GeoCode geoCode;

    public AirportResponse() {
    }

    public AirportResponse(Long id, String iataCode, String name, String detailedName, String timeZone, Address address, CityResponse city, GeoCode geoCode) {
        this.id = id;
        this.iataCode = iataCode;
        this.name = name;
        this.detailedName = detailedName;
        this.timeZone = timeZone;
        this.address = address;
        this.city = city;
        this.geoCode = geoCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDetailedName() {
        return detailedName;
    }

    public void setDetailedName(String detailedName) {
        this.detailedName = detailedName;
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

    public CityResponse getCity() {
        return city;
    }

    public void setCity(CityResponse city) {
        this.city = city;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(GeoCode geoCode) {
        this.geoCode = geoCode;
    }
}
