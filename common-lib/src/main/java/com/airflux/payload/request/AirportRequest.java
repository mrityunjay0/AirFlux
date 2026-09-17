package com.airflux.payload.request;

import com.airflux.payload.embeddable.Address;
import com.airflux.payload.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Long cityId;

    @Valid
    private GeoCode geoCode;

}
