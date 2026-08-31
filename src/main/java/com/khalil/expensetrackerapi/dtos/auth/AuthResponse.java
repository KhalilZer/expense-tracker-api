package com.khalil.expensetrackerapi.dtos.auth;

import com.khalil.expensetrackerapi.entities.User;

public record AuthResponse(
         User user,
         String token
) { }
