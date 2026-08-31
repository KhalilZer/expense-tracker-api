package com.khalil.expensetrackerapi.shared;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class GlobalResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;
    private final HttpStatus status;

    public static <T> ResponseEntity<GlobalResponse<T>> success(T data,
                                                                String message,
                                                                HttpStatus status) {

        return ResponseEntity.status(status).body(new GlobalResponse<>(true,
                data,
                message,
                status
        ));
    }
    public static <T> ResponseEntity<GlobalResponse<T>> error(T data,
                                                                String message,
                                                                HttpStatus status) {

        return ResponseEntity.status(status).body(new GlobalResponse<>(false,
                data,
                message,
                status
        ));
    }
}
