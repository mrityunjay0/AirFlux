package com.airflux.userService.service.serviceImpl;

import com.airflux.payload.dto.UserDTO;
import com.airflux.payload.enums.UserRole;
import com.airflux.payload.response.AuthResponse;
import com.airflux.userService.config.JwtProvider;
import com.airflux.userService.entity.User;
import com.airflux.userService.mapper.UserMapper;
import com.airflux.userService.repository.UserRepository;
import com.airflux.userService.service.AuthService;
import com.airflux.userService.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }


    /**

     1. Check if email already exists.
     2. Encode password using BCryptPasswordEncoder.
     3. Save user to the database.
     4. Generate JWT token for the user.
     5. Return AuthResponse with token and user details.

     **/

    @Override
    public AuthResponse signUp(UserDTO userDtoRequest) throws Exception {

        User user = userRepository.findByEmail(userDtoRequest.getEmail());

        // Check if user with given email already exists
        if(user != null) {
            throw new Exception("User with given email already exists.");
        }

        // Check role
        if(userDtoRequest.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("Cannot create user with role SYSTEM_ADMIN.");
        }

        // Create new user
        User newUser = User.builder()
                .email(userDtoRequest.getEmail())
                .password(passwordEncoder.encode(userDtoRequest.getPassword()))
                .fullName(userDtoRequest.getFullName())
                .phoneNumber(userDtoRequest.getPhoneNumber())
                .role(userDtoRequest.getRole())
                .createdAt(LocalDateTime.now())
                .lastLoginAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Save user to the database
        User savedUser = userRepository.save(newUser);

        // Create Authentication object for the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()
        );

        // Generate JWT token for the user
        String token = new JwtProvider().generateToken(authentication, savedUser.getId());

        return AuthResponse.builder()
                .jwtToken(token)
                .message("User registered successfully.")
                .title("Welcome to Airflux, " + savedUser.getFullName())
                .user(UserMapper.toUserDTO(savedUser))
                .build();
    }


    /**

        1. Load user by email
        2. Compare provided password with stored password using BCryptPasswordEncoder
        3. Update last login timestamp
        4. If password matches, generate JWT token
        5. Return AuthResponse with token and user details

     **/

    @Override
    public AuthResponse login(String email, String password) throws Exception {

        Authentication authentication = authenticate(email, password);

        User user = userRepository.findByEmail(email);
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);

        String token = new JwtProvider().generateToken(authentication, user.getId());

        return AuthResponse.builder()
                .jwtToken(token)
                .message("User logged in successfully.")
                .title("Welcome back, " + user.getFullName())
                .user(UserMapper.toUserDTO(user))
                .build();
    }

    private Authentication authenticate(String email, String password) throws Exception {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
