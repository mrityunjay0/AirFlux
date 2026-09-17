package com.airflux.payload.response;

import com.airflux.payload.embeddable.Address;
import com.airflux.payload.embeddable.GeoCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
