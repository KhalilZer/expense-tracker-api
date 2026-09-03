package com.khalil.expensetrackerapi.services;

import com.khalil.expensetrackerapi.abstracts.UserContract;
import com.khalil.expensetrackerapi.dtos.auth.AuthResponse;
import com.khalil.expensetrackerapi.dtos.auth.LoginRequest;
import com.khalil.expensetrackerapi.dtos.auth.RegisterRequest;
import com.khalil.expensetrackerapi.dtos.auth.UserResponse;
import com.khalil.expensetrackerapi.entities.User;
import com.khalil.expensetrackerapi.exceptions.EmailAlreadyExist;
import com.khalil.expensetrackerapi.exceptions.ResourceNotFound;
import com.khalil.expensetrackerapi.mappers.UserMapper;
import com.khalil.expensetrackerapi.reposotories.UserRepo;
import com.khalil.expensetrackerapi.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserContract {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    @Override
    public AuthResponse register(RegisterRequest payload) {
        if (userRepo.existsByEmail(payload.email())) {
            throw new EmailAlreadyExist("Email already exists");
        }
        String EncodedPassword = passwordEncoder.encode(payload.password());

        User user = userMapper.toEntity(payload);
        user.setPassword(EncodedPassword);

        User userSaved = userRepo.save(user);
        String token = jwtTokenProvider.generateToken(user);

        UserResponse userResponse = new UserResponse(
                userSaved.getId(),
                userSaved.getEmail(),
                userSaved.getFullName(),
                userSaved.getRole());

        return new AuthResponse(userResponse, token);

    }

    @Override
    public AuthResponse login(LoginRequest payload) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.email(), payload.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        assert userDetails != null;
        User user = userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFound("User not found")
                );

        String token = jwtTokenProvider.generateToken(user);


        UserResponse userResponse = new UserResponse(user.getId(), user.getEmail(), user.getFullName(), user.getRole());
        return new AuthResponse(userResponse, token);
    }

    @Override
    public User me() {
        return null;
    }
}
