package com.airflux.payload.request;

import com.airflux.payload.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineRequest {

    @NotBlank(message = "IATA code is required")
    @Size(min = 2, max = 2, message = "IATA code must be exactly 2 characters long")
    private String iataCode;

    @NotBlank(message = "ICAO code is required")
    @Size(min = 3, max = 3, message = "ICAO code must be exactly 3 characters long")
    private String icaoCode;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters long")
    private String name;

    private String alias;

    @NotBlank(message = "Country is required")
    private String country;

    private String logoUrl;
    private String website;

    private AirlineStatus status;

    private String alliance;

    private Long headQuaterCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;

}
