package com.airflux.payload.response;

import com.airflux.payload.dto.UserDTO;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String jwtToken;
    private String message;
    private String title;
    private UserDTO user;

}
