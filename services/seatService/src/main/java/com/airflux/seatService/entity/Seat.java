package com.airflux.seatService.entity;

import com.airflux.payload.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private Integer seatRow;

    private Character columnLetter;

    private SeatType seatType;

    private Double basePrice;

    private Double premiumSurCharge;

    @Builder.Default
    private Boolean isAvailable = true;

    @Builder.Default
    private Boolean isBlocked = false;

    @Builder.Default
    private Boolean isEmergencyExit = false;

    @Builder.Default
    private Boolean isActive = true;

    @Builder.Default
    private Boolean hasExtraLegRoom = false;

    @Builder.Default
    private Boolean hasPowerOutlet = false;

    @Builder.Default
    private Boolean hasTvScreen = false;

    @Builder.Default
    private Boolean hasExtraWidth = false;

    @Builder.Default
    private Boolean hasBassinet = false;

    @Builder.Default
    private Boolean isNearLavatory = false;

    @Builder.Default
    private Boolean isNearGallery = false;

    private Integer seatPitch;
    private Integer seatWidth;

    @ManyToOne(fetch = FetchType.LAZY)
    private SeatMap seatMap;

    @ManyToOne(fetch = FetchType.LAZY)
    private CabinClass cabinClass;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Version
    private Long version;



    public Double getTotalPrice() {

        Double totalPrice = basePrice != null ? basePrice : 0;

        if (premiumSurCharge != null && premiumSurCharge >= 0) {
            totalPrice += totalPrice + premiumSurCharge;
        }

        return totalPrice;
    }

    public Boolean isBookable() {

        return isActive && isAvailable && !isBlocked;
    }

    public String getFullPosition() {

        return seatRow + "" + columnLetter;
    }
}
