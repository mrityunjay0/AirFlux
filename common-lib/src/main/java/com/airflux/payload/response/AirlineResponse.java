package com.airflux.payload.response;

import com.airflux.payload.dto.UserDTO;
import com.airflux.payload.embeddable.Support;
import com.airflux.payload.enums.AirlineStatus;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineResponse {

    private Long id;

    private String iataCode;
    private String icaoCode;

    private String name;
    private String alias;

    private String logoUrl;
    private String website;

    private AirlineStatus status;
    private String alliance;

    private Long ownerId;
    private UserDTO owner;

    private Instant createdAt;
    private Instant updatedAt;
    private Long updatedById;

    private CityResponse headQuaterCity;
    private Support support;
}
