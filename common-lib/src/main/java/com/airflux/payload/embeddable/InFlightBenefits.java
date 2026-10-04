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
public class InFlightBenefits {

    @Column(name = "complimentary_meals", nullable = false)
    @Builder.Default
    private Boolean complimentaryMeals = false;

    @Column(name = "premium_meal_choice", nullable = false)
    @Builder.Default
    private Boolean premiumMealChoice = false;

    @Column(name = "inflight_internet", nullable = false)
    @Builder.Default
    private Boolean inflightInternet = false;

    @Column(name = "in_flight_entertainment", nullable = false)
    @Builder.Default
    private Boolean inFlightEntertainment = false;

    @Column(name = "complimentary_beverages", nullable = false)
    @Builder.Default
    private Boolean complimentaryBeverages = false;
}
