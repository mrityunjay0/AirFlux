package com.airflux.payload.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SeatBenefits {

    private Boolean extraSeatSpace = false;
    private Boolean preferredSeatChoice = false;
    private Boolean advanceSeatSelection = false;
    private Boolean guaranteedSeatTogether = false;
}
