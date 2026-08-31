package com.khalil.expensetrackerapi.abstracts;

import com.khalil.expensetrackerapi.dtos.auth.AuthResponse;
import com.khalil.expensetrackerapi.dtos.auth.LoginRequest;
import com.khalil.expensetrackerapi.dtos.auth.RegisterRequest;
import com.khalil.expensetrackerapi.entities.User;

public interface UserContract {
    AuthResponse register(RegisterRequest payload);
    AuthResponse login(LoginRequest payload);
    User me();
}
