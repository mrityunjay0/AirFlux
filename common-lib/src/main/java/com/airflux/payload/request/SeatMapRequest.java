package com.airflux.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatMapRequest {

    @NotBlank(message = "name is required")
    private String name;

    @Positive
    @NotNull(message = "totalRows is required")
    private Integer totalRows;

    @Positive
    @NotNull(message = "left seats are required")
    private Integer leftSeatsPerRow;

    @Positive
    @NotNull(message = "right seats are required")
    private Integer rightSeatsPerRow;

    private Long cabinClassId;
}
