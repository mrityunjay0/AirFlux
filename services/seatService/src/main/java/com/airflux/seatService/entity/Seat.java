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

    private Boolean isAvailable = true;

    private Boolean isBlocked = false;

    private Boolean isEmergencyExit = false;

    private Boolean isActive = true;

    private Boolean hasExtraLegRoom = false;

    private Boolean hasPowerOutlet = false;

    private Boolean hasTvScreen = false;

    private Boolean hasExtraWidth = false;

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
