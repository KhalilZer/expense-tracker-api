package com.khalil.expensetrackerapi.shared;

import com.khalil.expensetrackerapi.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<GlobalResponse<Void>> handleResourceNotFound(
            ResourceNotFound ex
    ) {
        return GlobalResponse.error(
                null,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }
}
