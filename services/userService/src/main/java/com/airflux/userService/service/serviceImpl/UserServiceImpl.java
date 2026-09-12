package com.airflux.userService.service.serviceImpl;

import com.airflux.payload.dto.UserDTO;
import com.airflux.payload.exception.ResourceNotFoundException;
import com.airflux.userService.entity.User;
import com.airflux.userService.mapper.UserMapper;
import com.airflux.userService.repository.UserRepository;
import com.airflux.userService.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO getUserByEmail(String email) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return UserMapper.toUserDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id)
        );

        return UserMapper.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }
}
