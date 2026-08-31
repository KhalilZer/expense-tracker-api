package com.khalil.expensetrackerapi.services;

import com.khalil.expensetrackerapi.abstracts.UserContract;
import com.khalil.expensetrackerapi.dtos.auth.AuthResponse;
import com.khalil.expensetrackerapi.dtos.auth.LoginRequest;
import com.khalil.expensetrackerapi.dtos.auth.RegisterRequest;
import com.khalil.expensetrackerapi.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserContract {
    @Override
    public AuthResponse register(RegisterRequest payload) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest payload) {
        return null;
    }

    @Override
    public User me() {
        return null;
    }
}
