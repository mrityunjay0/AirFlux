package com.airflux.payload.embeddable;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Support {

    private String email;
    private String phone;
    private String hours;
}
