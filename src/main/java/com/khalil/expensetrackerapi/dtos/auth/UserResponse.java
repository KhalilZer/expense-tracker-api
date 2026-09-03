package com.khalil.expensetrackerapi.dtos.auth;

import com.khalil.expensetrackerapi.enums.RoleEnum;

public record UserResponse(
        Long id,
        String email,
        String fullName,
        RoleEnum role
) {
}
