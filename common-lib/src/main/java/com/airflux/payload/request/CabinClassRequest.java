package com.airflux.payload.request;

import com.airflux.payload.enums.CabinClassType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinClassRequest {

    @NotNull(message = "Cabin type is required")
    private CabinClassType type;

    @NotBlank(message = "Code is required")
    private String code;

    private String description;

    @NotNull(message = "aircraftId is required")
    private Long aircraftId;

    private Integer displayOrder;

    private Boolean isActive;
    private Boolean isBookable;

    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;

    private String seatType;

}
