package com.khalil.expensetrackerapi.dtos.auth;

import com.khalil.expensetrackerapi.entities.User;
import com.khalil.expensetrackerapi.enums.RoleEnum;

public record AuthResponse(
        UserResponse user,
        String token
) {
}
