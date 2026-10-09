package com.airflux.seatService.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class FlightInstanceCabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long flightInstanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    private CabinClass cabinClass;

    private Integer totalSeats;

    private Integer bookedSeats = 0;

    // todo: seat instance
//    private List<SeatInstance> seatInstanceList = new ArrayList<>();

    private Integer getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

}
