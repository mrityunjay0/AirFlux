package com.airflux.seatService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int totalRows;

    @Column(nullable = false)
    private int rightSeatsPerRow;

    @Column(nullable = false)
    private int leftSeatsPerRow;

    @Column(nullable = false)
    private Long airlineId;

    // todo: watch
//    @OneToMany
//    private List<Seat> seats;

    @OneToOne
    private CabinClass cabinClass;
}
