package com.airflux.payload.dto;

import com.airflux.payload.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private UserRole role;
    private LocalDateTime lastLoginAt;

}
