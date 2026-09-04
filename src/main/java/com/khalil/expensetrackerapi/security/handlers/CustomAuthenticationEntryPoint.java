package com.khalil.expensetrackerapi.security.handlers;

import com.khalil.expensetrackerapi.shared.GlobalResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseEntity<GlobalResponse<Void>> errorResponse = GlobalResponse.error(null, "Unauthorized", HttpStatus.UNAUTHORIZED);

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getOutputStream(), errorResponse.getBody());
    }
}
