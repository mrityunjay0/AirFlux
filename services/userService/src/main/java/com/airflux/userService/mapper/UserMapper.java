package com.airflux.userService.mapper;

import com.airflux.payload.dto.UserDTO;
import com.airflux.userService.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
//                .password(user.getPassword())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }

    public static List<UserDTO> toUserDTO(List<User> users) {

        return users.stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

}