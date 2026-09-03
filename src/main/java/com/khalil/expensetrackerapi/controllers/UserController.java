package com.khalil.expensetrackerapi.controllers;

import com.khalil.expensetrackerapi.dtos.auth.AuthResponse;
import com.khalil.expensetrackerapi.dtos.auth.LoginRequest;
import com.khalil.expensetrackerapi.dtos.auth.RegisterRequest;
import com.khalil.expensetrackerapi.services.UserServiceImpl;
import com.khalil.expensetrackerapi.shared.GlobalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return GlobalResponse.success(
                userService.register(request),
                "User Created and logged successfully",
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        return GlobalResponse.success(
                userService.login(request),
                "User Logged successfully",
                HttpStatus.OK
        );
    }
}
