package com.khalil.expensetrackerapi.security.handlers;

import com.khalil.expensetrackerapi.shared.GlobalResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {


        ResponseEntity<GlobalResponse<Void>> errorResponse = GlobalResponse.error(null, "Access Denid", HttpStatus.FORBIDDEN);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        objectMapper.writeValue(response.getOutputStream(), errorResponse.getBody());
    }
}
