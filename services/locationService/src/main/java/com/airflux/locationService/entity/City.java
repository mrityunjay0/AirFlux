package com.airflux.locationService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cityCode;

    @Column(nullable = false)
    private String countryCode;

    @Column(nullable = false)
    private String countryName;

    @Size(max = 10)
    private String regionCode;

    @Column(name = "time_zone_id", nullable = false, length = 50)
    private String timeZone;


    public City() {
    }

    public City(long id, String name, String cityCode, String countryCode, String countryName, String regionCode, String timeZone) {
        this.id = id;
        this.name = name;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.regionCode = regionCode;
        this.timeZone = timeZone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
