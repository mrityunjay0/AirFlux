package com.airflux.payload.response;

import com.airflux.payload.dto.UserDTO;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthResponse {

    private String jwtToken;
    private String message;
    private String title;
    private UserDTO user;


    public AuthResponse() {
    }

    public AuthResponse(String jwtToken, String message, String title, UserDTO user) {
        this.jwtToken = jwtToken;
        this.message = message;
        this.title = title;
        this.user = user;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
