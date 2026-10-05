package com.airflux.pricingService.entity;

import com.airflux.payload.embeddable.*;
import com.airflux.payload.enums.CabinClassType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Character rbdCode;

    @Column(nullable = false)
    private Long flightId;

    @Column(nullable = false)
    private Long cabinClassId;

    @Enumerated(EnumType.STRING)
    private CabinClassType cabinClassType;

    @Column(nullable = false)
    private BigDecimal baseFare;

    private BigDecimal taxesAndFees;

    private BigDecimal airlineFees;

    private BigDecimal currentPrice;

    @Column(nullable = false)
    private String fareLabel;

    @OneToOne(mappedBy = "fare", cascade = CascadeType.ALL, orphanRemoval = true)
    private BaggagePolicy baggagePolicy;

    @OneToOne(mappedBy = "fare", cascade = CascadeType.ALL, orphanRemoval = true)
    private FareRules fareRules;

    @Embedded
    @Builder.Default
    private SeatBenefits seatBenefits = new SeatBenefits();

    @Embedded
    @Builder.Default
    private BoardingBenefits boardingBenefits = new BoardingBenefits();

    @Embedded
    @Builder.Default
    private InFlightBenefits inFlightBenefits = new InFlightBenefits();

    @Embedded
    @Builder.Default
    private FlexibilityBenefits flexibilityBenefits = new FlexibilityBenefits();

    @Embedded
    @Builder.Default
    private PremiumServiceBenefits premiumServiceBenefits = new PremiumServiceBenefits();

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;


    // Calculate total price by summing baseFare, taxesAndFees, and currentPrice
    public BigDecimal getTotalPrice() {

        BigDecimal base = baseFare != null ? baseFare : BigDecimal.ZERO;
        BigDecimal taxes = taxesAndFees != null ? taxesAndFees : BigDecimal.ZERO;
        BigDecimal current = currentPrice != null ? currentPrice : BigDecimal.ZERO;

        return base.add(taxes).add(current);
    }

}
