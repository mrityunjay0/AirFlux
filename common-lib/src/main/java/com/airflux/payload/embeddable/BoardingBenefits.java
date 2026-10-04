package com.airflux.payload.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BoardingBenefits {

    @Column(name = "priority_boarding", nullable = false)
    @Builder.Default
    private Boolean priorityBoarding = false;

    @Column(name = "priority_check_in", nullable = false)
    @Builder.Default
    private Boolean priorityCheckIn = false;

    @Column(name = "fast_track_security", nullable = false)
    @Builder.Default
    private Boolean fastTrackSecurity = false;
}
