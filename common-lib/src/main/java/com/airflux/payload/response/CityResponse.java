package com.airflux.payload.response;

import lombok.Builder;

@Builder
public class CityResponse {

    private Long id;
    private String name;
    private String cityCode;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String timeZone;

    public CityResponse() {
    }

    public CityResponse(Long id, String name, String cityCode, String countryCode, String countryName, String regionCode, String timeZone) {
        this.id = id;
        this.name = name;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.timeZone = timeZone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
