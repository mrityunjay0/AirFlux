package com.airflux.userService.service;

import com.airflux.payload.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
}
