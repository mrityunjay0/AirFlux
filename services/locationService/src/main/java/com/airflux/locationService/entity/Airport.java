package com.airflux.locationService.entity;

import com.airflux.payload.embeddable.Address;
import com.airflux.payload.embeddable.GeoCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZoneId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private City city;


    @JsonIgnore
    @Transient
    public String getDetailedName(){
        if(city != null && city.getCountryCode() != null){
            return name.toUpperCase() + "/" + city.getCountryCode();
        }
        return name.toUpperCase();
    }

}
