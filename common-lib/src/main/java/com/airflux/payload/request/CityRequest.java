package com.airflux.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CityRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @NotBlank(message = "City code is required")
    @Size(min = 1, max = 10)
    private String cityCode;

    @NotBlank(message = "Country code is required")
    @Size(min = 1, max = 10)
    private String countryCode;

    @NotBlank(message = "Country name is required")
    @Size(min = 1, max = 50)
    private String countryName;

    @Size(min = 1, max = 10)
    private String regionCode;

    @Size(min = 1, max = 50)
    private String timeZone;


    public CityRequest() {
    }

    public CityRequest(String name, String cityCode, String countryCode, String countryName, String regionCode, String timeZone) {
        this.name = name;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.timeZone = timeZone;
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
