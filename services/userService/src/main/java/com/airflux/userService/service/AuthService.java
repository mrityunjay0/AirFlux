package com.airflux.userService.service;

import com.airflux.payload.dto.UserDTO;
import com.airflux.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signUp(UserDTO userDtoRequest) throws Exception;
}
